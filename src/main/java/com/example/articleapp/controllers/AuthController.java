package com.example.articleapp.controllers;

import com.example.articleapp.DB.DB_AUTH_and_REG;
import com.example.articleapp.HelloApplication;
import com.example.articleapp.animation.Shake;
import com.example.articleapp.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Label mainLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label regLable;

    @FXML
    private Button reg_btn;

    @FXML
    private Button sign_btn;

    @FXML
    void initialize() {
            reg_btn.setOnAction(event -> {
                new HelloApplication().nextPage(reg_btn.getScene().getWindow(), "reg.fxml");
            });

            sign_btn.setOnAction(event -> {
                if (validation(nameField.getText(), PasswordField.getText())) {
                    new HelloApplication().nextPage(sign_btn.getScene().getWindow(), "articles.fxml");
                    User.authUser(nameField.getText(), PasswordField.getText());
                }
            });
        }

    private boolean validation(String login, String password) {
        boolean validation = false;
        Shake shake = new Shake(sign_btn);
        if (!DB_AUTH_and_REG.isExist(login)) {
            nameField.setStyle("-fx-border-color: red");
            regLable.setText("Такой пользователь не существует!");
            regLable.setStyle("-fx-text-fill: orange");
            shake.playAnimation();
        } else if (!DB_AUTH_and_REG.authentication(login, password)) {
            nameField.setStyle("-fx-border-color: red");
            PasswordField.setStyle("-fx-border-color: red");
            regLable.setText("Неверный логин или пароль!");
            regLable.setStyle("-fx-text-fill: orange;");
            shake.playAnimation();
        } else {
            validation = true;
        }
        return validation;
    }
}