package com.example.gestionproduit;

import com.example.gestionproduit.model.Db;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart;

    @FXML
    private PieChart piechart;
    Connection connection= null ;
    Db db ;
    public void Graphe() {
        try {
            String sql = "select count(*) as nb, category.name from category, product where product.category_id = category.id group by category.name";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            barchart.getXAxis().setLabel("Catégorie");
            barchart.getYAxis().setLabel("Nombre de produits");
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("name");
                int productCount = resultSet.getInt("nb");
                series.getData().add(new XYChart.Data<>(categoryName, productCount));
            }
            barchart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db = new Db();
        connection = db.getConnection();
        Graphe();
    }
}
