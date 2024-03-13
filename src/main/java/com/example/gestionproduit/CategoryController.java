package com.example.gestionproduit;

import com.example.gestionproduit.Repository.CategoryRepository;
import com.example.gestionproduit.model.Category;
import com.example.gestionproduit.model.Db;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

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

    @FXML
    void btnAdd(ActionEvent event) {
        String sql="insert into categorie(libelle) values(?)";
        try {
            PreparedStatement statement=new Db().getConnection().prepareStatement(sql);
            statement.setString(1,nameInput.getText());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        affiche();
        nameInput.setText("");
    }

    //@FXML
   /* void btnAnnuler(ActionEvent event) {
        nameInput.setText("");
    }*/

    @FXML
    void btnDelete(ActionEvent event) {
        String sql="delete from categorie where id=?";
        try {
            PreparedStatement statement=new Db().getConnection().prepareStatement(sql);
            statement.setInt(1,1);
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
            nameInput.setText(categorie.getName());
        }
    }
    public void affiche(){
        CategoryRepository categorieRepository=new CategoryRepository();
        ObservableList<Category> list=categorieRepository.getAllCategorie();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        categoryTable.setItems(list);
    }



    @FXML
    void btnEdit(ActionEvent event) {

    }

    @FXML
    void searchInput(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Db().getConnection();
        affiche();
    }
}
