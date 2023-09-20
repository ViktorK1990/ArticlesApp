package com.example.articleapp.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Actions {
    public static String getArticle (String tittle) {
        String query = "SELECT text FROM articles WHERE tittle = '" + tittle + "'";
        DB_Connection mySQLConnection = new DB_Connection();
        String text = "";
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                text = res.getString("text");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static ResultSet getAllArticle () {
        String query = "SELECT * FROM articles";
        DB_Connection mySQLConnection = new DB_Connection();
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
