module com.example.gestionproduit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestionproduit to javafx.fxml;
    exports com.example.gestionproduit;
}