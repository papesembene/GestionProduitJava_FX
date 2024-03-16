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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    Db db;
    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, Integer> colId;

    @FXML
    private TableColumn<Category, String> colName;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField searchInput;
    Connection connection;
    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;
    @FXML
    void btnAdd(ActionEvent event) {
        String sql="insert into category(name) values(?)";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setString(1,nameInput.getText());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         this.affiche();
        nameInput.setText("");
    }

    //@FXML
   /* void btnAnnuler(ActionEvent event) {
        nameInput.setText("");
    }*/

    @FXML
    void btnDelete(ActionEvent event) {
        int id=this.categoryTable.getSelectionModel().getSelectedItem().getId();
        String sql="delete from category where id=?";
        try {
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
    }
    @FXML
    void charge(MouseEvent event) {
        Category categorie=(Category) categoryTable.getSelectionModel().getSelectedItem();
        if (event.getClickCount()==2){
            btnadd.setDisable(true);
            btndelete.setDisable(true);
            nameInput.setText(categorie.getName());
        }
    }
    public void affiche(){
        CategoryRepository categorieRepository = new CategoryRepository();
        ObservableList<Category> list = categorieRepository.getAllCategorie();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTable.setItems(list);
    }



    @FXML
    void btnEdit(ActionEvent event) {
        int id = this.categoryTable.getSelectionModel().getSelectedItem().getId();
        String sql = "UPDATE category SET name=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nameInput.getText());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
        btnadd.setDisable(false);
        btndelete.setDisable(false);
        nameInput.setText("");
    }
    @FXML
    void searchInput(KeyEvent event) {
        CategoryRepository cat =new CategoryRepository();
        ObservableList<Category> list = cat.search(searchInput.getText());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryTable.setItems(list);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         db = new Db();
        connection= db.getConnection();
        affiche();
    }
}
