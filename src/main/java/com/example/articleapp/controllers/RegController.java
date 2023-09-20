package com.example.articleapp.controllers;

import com.example.articleapp.DB.DB_AUTH_and_REG;
import com.example.articleapp.HelloApplication;
import com.example.articleapp.animation.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegController {

    @FXML
    private Label reg_label;
    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView back_btn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button reg_btn;

    @FXML
    void initialize() {
        back_btn.setOnMouseClicked(event -> {
            new HelloApplication().nextPage(reg_btn.getScene().getWindow(), "auth.fxml");
        });


        reg_btn.setOnAction(event -> {
            if (regValidation(nameField.getText(), passwordField.getText(), emailField.getText(), phoneField.getText())) {
                new DB_AUTH_and_REG().createUser(nameField.getText(), emailField.getText(), passwordField.getText(), phoneField.getText());
                success();
            }
        });
    }

    private boolean regValidation(String login, String password, String email, String phone) {
        boolean validation = false;
        Shake shake = new Shake(reg_btn);
        if (login.contains("@") || login.contains("%")) {
            nameField.setStyle("-fx-border-color: red;");
            reg_label.setStyle("fx-text-fill: orange");
            reg_label.setText("Логин содержит недопустимые символы");
            shake.playAnimation();
        } else if (DB_AUTH_and_REG.isExist(login)) {
            nameField.setStyle("-fx-border-color: red");
            reg_label.setStyle("fx-text-fill: orange");
            reg_label.setText("Такой пользователь существует");
            shake.playAnimation();
        } else if (password.length() < 5) {
            passwordField.setStyle("-fx-border-color: red");
            reg_label.setStyle("fx-text-fill: orange");
            reg_label.setText("Пароль менее 5 символов");
            shake.playAnimation();
        } else if (!email.contains("@") || !email.contains(".") || email.isEmpty()) {
            emailField.setStyle("-fx-border-color: red");
            reg_label.setStyle("fx-text-fill: orange");
            reg_label.setText("Некорректный email");
            shake.playAnimation();
        } else if (phone.length() < 5) {
            phoneField.setStyle("-fx-border-color: red");
            reg_label.setStyle("fx-text-fill: orange");
            reg_label.setText("Слишком короткий номер");
        } else {
            validation = true;
        }
        return validation;
    }

    private void success() {
        nameField.setVisible(false);
        passwordField.setVisible(false);
        emailField.setVisible(false);
        phoneField.setVisible(false);
        reg_label.setStyle("-fx-text-fill: orange");
        reg_label.setText("Вы успешно зарегистрировались!");
        reg_btn.setText("На главную");
        reg_btn.setOnAction(event -> {
            new HelloApplication().nextPage(reg_btn.getScene().getWindow(), "auth.fxml");
        });

    }
}
