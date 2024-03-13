package com.example.gestionproduit.Repository;

import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.Product;
import com.example.gestionproduit.model.User;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository {
    private Connection connection;

    public ProductRepository() {
        this.connection = new Db().getConnection();
    }
   /* List<Product> getProduitsQuantiteInferieure(int qte){
        this.connection = new Db().getConnection();
        //User user = new User();
        try {
            String sql = "SELECT * FROM product WHERE quantity < ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/
   public ObservableList<Product> getAllproducts(){
       this.connection = new Db().getConnection();
       ObservableList<Product> list = null;
       try {
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
