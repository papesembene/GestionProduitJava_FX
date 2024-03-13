package com.example.gestionproduit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController  implements Initializable {
    @FXML
    private PasswordField mdpInput;

    @FXML
    private TextField userInput;

    private UserRepository userRepository= new UserRepository();

    @FXML
    void btnLogin(ActionEvent event)  throws IOException {
        String login = userInput.getText();
        String mdp = mdpInput.getText();
        if (login.equals("") || mdp.equals("")){
            System.out.println("Veuillez remplir tous les champs");
        }else{

        }
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
