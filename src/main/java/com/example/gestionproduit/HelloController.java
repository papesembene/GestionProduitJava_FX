package com.example.gestionproduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private PasswordField mdpInput;

    @FXML
    private TextField userInput;

    @FXML
    void btnLogin(ActionEvent event) {

    }

    @FXML
    void bntInscrire(ActionEvent event) throws IOException {
        Parent signin = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(signin);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
