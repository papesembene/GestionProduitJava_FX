package com.example.gestionproduit.Repository;

import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
     Connection connection ;
    Db db ;
    public User getUser(String login, String mdp) {

        Db db = new Db();
        connection = db.getConnection();
        User user = new User();
        try {
            String sql = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, mdp);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
