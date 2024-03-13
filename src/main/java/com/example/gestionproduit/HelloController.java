package com.example.gestionproduit;
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
    @FXML
    private String name;

    private final UserRepository userRepository= new UserRepository();

    @FXML
    void btnLogin(ActionEvent event)  throws IOException {
        String login = userInput.getText();
        String mdp = mdpInput.getText();
        if (login.equals("") || mdp.equals("")) {
            System.out.println("Veuillez remplir tous les champs");
        } else {
            User user = userRepository.getUser(login, mdp);
            if (userRepository != null) {
                name = "BONJOUR" + (user.getName());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Connexion reuissi");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté");
                alert.showAndWait();
                // Affichage de la liste des produits avec une quantité inférieure à cinq
               /* List<Product> produits = produitDao.getProduitsQuantiteInferieure(5);
                if (!produits.isEmpty()) {
                    StringBuilder message = new StringBuilder("Liste des produits avec une quantité inférieure à cinq :\n");
                    for (Product produit : produits) {
                        message.append(produit.getNom()).append(": ").append(produit.getQuantite()).append("\n");
                    }
                    Alert alertListeProduits = new Alert(Alert.AlertType.INFORMATION);
                    alertListeProduits.setTitle("Produits avec quantité inférieure à cinq");
                    alertListeProduits.setHeaderText(null);
                    alertListeProduits.setContentText(message.toString());
                    alertListeProduits.showAndWait();*/
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Accueil");
                    stage.setScene(scene);
                    stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Login ou mot de pass incorrect");
                        alert.showAndWait();
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
