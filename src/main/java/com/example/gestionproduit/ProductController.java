package com.example.gestionproduit;

import com.example.gestionproduit.model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductController {

    @FXML
    private ComboBox<Category> categoryCombo;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQte;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField searchInput;

    @FXML
    private TextField priceInput;

    @FXML
    private TableView<?> productTable;

    @FXML
    private TextField qteInput;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnEdit(ActionEvent event) {

    }

    @FXML
    void searchInput(ActionEvent event) {

    }

}
