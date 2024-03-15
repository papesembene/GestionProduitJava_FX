package com.example.gestionproduit;

import com.example.gestionproduit.Repository.CategoryRepository;
import com.example.gestionproduit.Repository.ProductRepository;
import com.example.gestionproduit.model.Category;
import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    Db db;
    Connection connection =null;
    @FXML
    private ComboBox<Category> categoryCombo;

    @FXML
    private TableColumn<Product, String> colCategory;

    @FXML
    private TableColumn<Product, Integer> colId;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Integer> colPrice;

    @FXML
    private TableColumn<Product, Integer> colQte;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField searchInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TextField qteInput;
    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnedit;

    private int getIdCategorie(String name) throws SQLException {
        String sql = "select id from category where name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, String.valueOf(name));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            throw new IllegalArgumentException("Catégorie introuvable : " + name);
        }
    }

    @FXML
    void btnAdd(ActionEvent event) {
        String sql="insert into product(name,price,quantity,category_id) values(?,?,?,?)";
        try {
            PreparedStatement statement=new Db().getConnection().prepareStatement(sql);
            statement.setString(1,nameInput.getText());
            statement.setInt(2, Integer.parseInt(priceInput.getText()));
            statement.setInt(3, Integer.parseInt(qteInput.getText()));
            statement.setInt(4, getIdCategorie(categoryCombo.getValue().getName()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
        nameInput.setText("");
        priceInput.setText("");
        qteInput.setText("");
        categoryCombo.setValue(null);
    }

        public void affiche(){
            ProductRepository prod = new ProductRepository();
            ObservableList<Product> list;
            list = prod.getAllproducts();
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colQte.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            //String namecategory = String.valueOf(categoryCombo.getValue());
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category_id"));
            productTable.setItems(list);
    }

    @FXML
    void btnDelete(ActionEvent event) {
        int id=this.productTable.getSelectionModel().getSelectedItem().getId();
        try {
            String sql=" DELETE FROM product WHERE id = ?";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        affiche();

    }
    @FXML
    void charger(MouseEvent event) throws SQLException {
        Product prod =(Product) productTable.getSelectionModel().getSelectedItem();
        if (event.getClickCount()==2){
            btnadd.setDisable(true);
            btndelete.setDisable(true);
            priceInput.setText(String.valueOf(prod.getPrice()));
            nameInput.setText(prod.getName());
            qteInput.setText(String.valueOf(prod.getQuantity()));
            CategoryRepository categorieRepository=new CategoryRepository();
            int id = categoryCombo.getValue().getId();
            ObservableList<Category> categorie = categorieRepository.getAllCategorie();
            categoryCombo.setItems(categorie);
        }
    }


    @FXML
    void btnEdit(ActionEvent event) {
        int id = this.productTable.getSelectionModel().getSelectedItem().getId();
        String sql = "UPDATE product SET name=? , price =?,quantity=? , category_id=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nameInput.getText());
            statement.setInt(2, Integer.parseInt(priceInput.getText()));
            statement.setInt(3, Integer.parseInt(qteInput.getText()));
            statement.setInt(4, categoryCombo.getValue().getId());
            //statement.setInt(4, categoryCombo.getValue().getId());
            statement.setInt(5, id);
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
        btnadd.setDisable(false);
        btndelete.setDisable(false);
        nameInput.setText("");
        priceInput.setText("");
        qteInput.setText("");
        categoryCombo.setItems(null);
    }

    @FXML
    void searchInput(KeyEvent event) {
        ProductRepository prod =new ProductRepository();
        ObservableList<Product> list = prod.search(searchInput.getText());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQte.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTable.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db = new Db();
        connection = db.getConnection();
        //connection=new Db().getConnection();
        affiche();
        CategoryRepository categorieRepository=new CategoryRepository();
        ObservableList<Category> categorie = categorieRepository.getAllCategorie();
        categoryCombo.setItems(categorie);
    }
}
