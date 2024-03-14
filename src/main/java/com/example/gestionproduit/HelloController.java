package com.example.gestionproduit;
import com.example.gestionproduit.Repository.ProductRepository;
import com.example.gestionproduit.Repository.UserRepository;
import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.Product;
import com.example.gestionproduit.model.User;
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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
public class HelloController  implements Initializable {
    @FXML
    private PasswordField mdpInput;

    @FXML
    private TextField userInput;

    public static String name;


    @FXML
    void btnLogin(ActionEvent event) throws IOException
    {
        UserRepository userRepository = new UserRepository();
        String login = userInput.getText();
        String mdp = mdpInput.getText();
            if (login.equals("") || mdp.equals(""))
            {
                // System.out.println("Veuillez remplir tous les champs");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ALERT");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs");
                alert.showAndWait();
            }
            else
            {
                User user = userRepository.getUser(login, mdp);
                    if (userRepository != null)
                    {
                        name = "Bienvenue cher (e) " + " " + (user.getLogin());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Connexion reuissi");
                        alert.setHeaderText(null);
                        alert.setContentText("Vous êtes maintenant connecté");
                        alert.showAndWait();
                        // Affichage de la liste des produits avec une quantité inférieure à cinq
                        ProductRepository prod = new ProductRepository();
                        List<Product> produits = prod.getProduitsQuantiteInferieure();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setTitle("Accueil");
                        stage.setScene(scene);
                        stage.show();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("quantity more less 5");
                        alert.setHeaderText(null);
                        alert.setContentText(produits.toString());
                        alert.showAndWait();
                    }
                    else
                    {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Erreur");
                        alert1.setHeaderText(null);
                        alert1.setContentText("Login ou mot de pass incorrect");
                        alert1.showAndWait();
                   }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = new Db().getConnection();
    }

}