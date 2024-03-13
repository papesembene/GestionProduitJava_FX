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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private ComboBox<Category> categoryCombo;

    @FXML
    private TableColumn<Product, Integer> colCategory;

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
    Connection connection;

    @FXML
    void btnAdd(ActionEvent event) {
        String sql="insert into product(name,price,quantity,category_id) values(?,?,?,?)";
        try {
            PreparedStatement statement=new Db().getConnection().prepareStatement(sql);
            statement.setString(1,nameInput.getText());
            statement.setInt(2, Integer.parseInt(priceInput.getText()));
            statement.setInt(3, Integer.parseInt(qteInput.getText()));
            statement.setInt(4, Integer.parseInt(String.valueOf(categoryCombo.getValue())));
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
    void btnEdit(ActionEvent event) {

    }

    @FXML
    void searchInput(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection=new Db().getConnection();
        affiche();
        CategoryRepository categorieRepository=new CategoryRepository();
        ObservableList<Category> categorie = categorieRepository.getAllCategorie();
        categoryCombo.setItems(categorie);
    }
}
