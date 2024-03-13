module com.example.gestionproduit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestionproduit to javafx.fxml;
    exports com.example.gestionproduit;
    exports com.example.gestionproduit.Repository;
    opens com.example.gestionproduit.Repository to javafx.fxml;
}