package com.example.gestionproduit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainpageController {

    @FXML
    private Pane contentpage;

    @FXML
    private TextField name;

    @FXML
    void categorypage(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("category.fxml"));
        contentpage.getChildren().removeAll();
        contentpage.getChildren().setAll(fxml);

    }


    @FXML
    void dashboardpage(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("statistique.fxml"));
        contentpage.getChildren().removeAll();
        contentpage.getChildren().setAll(fxml);
    }

    @FXML
    void docspage(ActionEvent event) {

    }

    @FXML
    void productpage(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("product.fxml"));
        contentpage.getChildren().removeAll();
        contentpage.getChildren().setAll(fxml);
    }

}
