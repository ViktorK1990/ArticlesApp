package com.example.articleapp.DB;

import com.example.articleapp.models.Password;
import com.example.articleapp.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_AUTH_and_REG {
    public void createUser (String login, String email, String password, String phone) {
        String query = "INSERT INTO users (login, email, password, phone) VALUES (?,?,?,?)";
        DB_Connection mySQLConnection = new DB_Connection();
        try {
            PreparedStatement preparedStatement = mySQLConnection.getDBConnection().prepareStatement(query);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3, Password.md5String(password));
            preparedStatement.setString(4, phone);
            preparedStatement.executeUpdate();
            System.out.println("User added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeData (String newEmail, String newLogin, String newPassword, String newPhone) {
        String query = "UPDATE users SET login = ? , password = ? , email = ?, phone = ? WHERE login = ? AND password = ?;";
        DB_Connection mySQLConnection = new DB_Connection();
        try {
            PreparedStatement preparedStatement = mySQLConnection.getDBConnection().prepareStatement(query);
            preparedStatement.setString(1,newLogin);
            preparedStatement.setString(2,Password.md5String(newPassword));
            preparedStatement.setString(3, newEmail);
            preparedStatement.setString(4, newPhone);
            preparedStatement.setString(5, User.getUser().getLogin());
            preparedStatement.setString(6, User.getUser().getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User changed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean authentication(String login, String password) {
        boolean result = false;
        String query = "SELECT id FROM users WHERE login = '" + login + "' AND password = '" + Password.md5String(password) + "';";
        DB_Connection mySQLConnection = new DB_Connection();
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            ResultSet res = statement.executeQuery(query);
            result = res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isExist(String login) {
        boolean result = false;
        String query = "SELECT id FROM users WHERE login = '" + login + "';";
        DB_Connection mySQLConnection = new DB_Connection();
        try {
            Statement statement = mySQLConnection.getDBConnection().createStatement();
            ResultSet res = statement.executeQuery(query);
            result = res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
