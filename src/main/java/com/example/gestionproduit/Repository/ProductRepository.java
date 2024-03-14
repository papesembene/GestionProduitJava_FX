package com.example.gestionproduit.Repository;

import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.Product;
import com.example.gestionproduit.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository {
    private Connection connection;
    Db db = new Db();

    public ProductRepository() {
        this.connection = new Db().getConnection();
    }
   public List<Product> getProduitsQuantiteInferieure() {
       this.connection = new Db().getConnection();
       ObservableList<Product> list = FXCollections.observableArrayList();
       //list = null;
       try {
           String sql = "SELECT * FROM product WHERE quantity < 5 ";
           PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery();
           if (rs.next()) {
               Product product = new Product();
               product.setId(rs.getInt(1));
               product.setName(rs.getString(2));
               product.setPrice(rs.getInt(3));
               product.setQuantity(rs.getInt(4));
               product.setCategory_id(rs.getInt(5));
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return list;

   }
   public ObservableList<Product> getAllproducts(){
       connection = db.getConnection();
       ObservableList<Product> list = null;
       try {
           list = FXCollections.observableArrayList();
           String sql = "SELECT * FROM product  ";
           PreparedStatement statement = connection.prepareStatement(sql);
           ResultSet rs = statement.executeQuery();
           while (rs.next()) {
               Product prod = new Product();
               prod.setId(rs.getInt(1));
               prod.setName(rs.getString(2));
               prod.setPrice(rs.getInt(3));
               prod.setQuantity(rs.getInt(4));
               prod.setCategory_id(rs.getInt(5));
              list.add(prod);

           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return list;

   }


}
