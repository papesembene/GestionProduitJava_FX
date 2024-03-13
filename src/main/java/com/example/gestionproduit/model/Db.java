package com.example.gestionproduit.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    private final String server = "localhost";
    private final String username = "postgres";
    private final String password = "passer";
    private final String bd = "gestion_produits_fx";
    private final String url = "jdbc:postgresql://localhost:5432/gestion_produits_fx";
    private Connection conn;

    public Db() {
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gestion_produits_fx", "postgres", "passer");
            //System.out.println("Connection a la base reussi ");
        } catch (Exception var2) {
            this.conn = null;
            System.out.print("Erreur de Connection  ");
        }

        return this.conn;
    }
}
