package com.example.gestionproduit.Repository;

import com.example.gestionproduit.model.Db;
import com.example.gestionproduit.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;
    public UserRepository() {
        this.connection = new Db().getConnection();
    }
    public User getUser(String login, String mdp) {
        this.connection = new Db().getConnection();
        User user = new User();
        try {
            String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, mdp);
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

        return user;
    }
}
