package com.example.gestionproduit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainpageController implements Initializable {

    @FXML
    private Pane contentpage;

    @FXML
    private Label name;

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
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product.fxml")));
        contentpage.getChildren().removeAll();
        contentpage.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(HelloController.name);
    }
}
