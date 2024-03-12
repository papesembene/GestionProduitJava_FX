package com.example.gestionproduit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private PasswordField mdp1Input;

    @FXML
    private PasswordField mdp2Input;

    @FXML
    private TextField userInput;

    @FXML
    void btn_inscription(ActionEvent event) {

    }

    @FXML
    void btn_login(ActionEvent event)  throws IOException {
        Parent signin = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(signin,700, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

