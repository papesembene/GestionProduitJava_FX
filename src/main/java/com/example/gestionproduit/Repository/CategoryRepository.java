package com.example.gestionproduit.Repository;

import com.example.gestionproduit.model.Category;
import com.example.gestionproduit.model.Db;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepository {
    public ObservableList<Category> getAllCategorie() {
       Connection connection = new Db().getConnection();
        ObservableList<Category> list = null;
        try {
            list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM categorie ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category categorie = new Category();
                categorie.setId(rs.getInt(1));
                categorie.setName(rs.getString(2));
                list.add(categorie);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
