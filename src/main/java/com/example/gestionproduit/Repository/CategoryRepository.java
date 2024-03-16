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
    Connection connection;
    Db db = new Db();

    public ObservableList<Category> getAllCategorie() {
       Connection connection = db.getConnection();
        ObservableList<Category> list = null;
        try {
            list = FXCollections.observableArrayList();
            String sql = "SELECT *  FROM category ";
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
    //pour rechercher un produit
    public ObservableList<Category> search(String text) {
        connection = db.getConnection();
        // List<Medecin> liste = null;
        ObservableList<Category> list = null;
        try {
            list = FXCollections.observableArrayList();
            String sql = "SELECT * FROM category WHERE lower(name) LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + text + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getInt(1));
                cat.setName(rs.getString(2));
                list.add(cat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public Category getCategoryById(int id) {
        Category category = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM category WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return category;
    }
}
