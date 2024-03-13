package com.example.gestionproduit;

import com.example.gestionproduit.Repository.UserRepository;
import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField mdp1Input;

    @FXML
    private PasswordField mdp2Input;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private TextField userInput;
    UserRepository UserRepository;
    @FXML
    void btn_inscription(ActionEvent event) throws IOException {
        String login = userInput.getText();
        String mdp = mdp1Input.getText();
        String mdp1 = mdp2Input.getText();
        if (!mdp.equals(mdp1)) {
            System.out.println("password no conforme");
        }
        if (login.equals("") || mdp.equals("") || mdp1.equals("") || phoneInput.equals("")|| nameInput.equals("") || emailInput.equals("")) {
            System.out.println("Veuillez remplir tous les champs");
        } else {
            String sql="insert into user(name,telephone,email,login,password) values(?,?,?,?,?)";
            try{
                PreparedStatement statement=new Db().getConnection().prepareStatement(sql);
                statement.setString(1,nameInput.getText());
                statement.setString(2,phoneInput.getText());
                statement.setString(3,emailInput.getText());
                statement.setString(4,userInput.getText());
                statement.setString(5,mdp1Input.getText());
                statement.execute();
                //Navigation vers la page login
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage=new Stage();
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            nameInput.setText("");
            phoneInput.setText("");
            userInput.setText("");
            mdp1Input.setText("");
            mdp2Input.setText("");
            emailInput.setText("");



        }
        }




            @FXML
            void btn_login (ActionEvent event)  throws IOException {
                Parent signin = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Scene scene = new Scene(signin, 700, 500);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }


    }

