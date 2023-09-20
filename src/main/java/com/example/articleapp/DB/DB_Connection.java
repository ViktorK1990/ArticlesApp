package com.example.articleapp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private  final String DB_HOST = "localhost";
    private  final String DB_PORT = "3307";
    private  final String DB_NAME = "mydatabase";
    private  final String DB_USER_NAME = "root";
    private  final String DB_PASSWORD = "root";

    private Connection connection = null;

    public Connection getDBConnection() {
        String connectionQuery = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionQuery, DB_USER_NAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public  void isConnected() {
        try {
            System.out.println(getDBConnection().isValid(1000));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
