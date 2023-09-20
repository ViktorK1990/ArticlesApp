package com.example.articleapp.controllers;

import com.example.articleapp.DB.DB_AUTH_and_REG;
import com.example.articleapp.DB.DB_Actions;
import com.example.articleapp.HelloApplication;
import com.example.articleapp.animation.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LkController {

    @FXML
    private Label lk_label;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private ImageView back_btn;

    @FXML
    private Button change_btn;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    void initialize() {
        change_btn.setOnAction(event -> {
            if (regValidation(nameField.getText(), PasswordField.getText(), emailField.getText(), phoneField.getText())) {
                new DB_AUTH_and_REG().changeData(emailField.getText(), nameField.getText(), PasswordField.getText(), phoneField.getText());
                success();
            }
        });

        back_btn.setOnMouseClicked(mouseEvent -> {
            new HelloApplication().nextPage(back_btn.getScene().getWindow(), "articles.fxml");
        });
    }

    private boolean regValidation(String login, String password, String email, String phone) {
        boolean validation = false;
        Shake shake = new Shake(change_btn);
        if (login.contains("@") || login.contains("%")) {
            nameField.setStyle("-fx-border-color: red;");
            lk_label.setStyle("fx-text-fill: orange");
            lk_label.setText("Логин содержит недопустимые символы!");
            shake.playAnimation();
        } else if (DB_AUTH_and_REG.isExist(login)) {
            nameField.setStyle("-fx-border-color: red");
            lk_label.setStyle("fx-text-fill: orange");
            lk_label.setText("Такой пользователь существует!");
            shake.playAnimation();
        } else if (password.length() < 5) {
            PasswordField.setStyle("-fx-border-color: red");
            lk_label.setStyle("fx-text-fill: orange");
            lk_label.setText("Пароль менее 5 символов!");
            shake.playAnimation();
        } else if (!email.contains("@") || !email.contains(".") || email.isEmpty()) {
            emailField.setStyle("-fx-border-color: red");
            lk_label.setStyle("fx-text-fill: orange");
            lk_label.setText("Некорректный email!");
            shake.playAnimation();
        } else if (phone.length() < 5) {
            phoneField.setStyle("-fx-border-color: red");
            lk_label.setStyle("fx-text-fill: orange");
            lk_label.setText("Слишком короткий номер!");
        } else {
            validation = true;
        }
        return validation;
    }

    public void success() {
        lk_label.setStyle("fx-text-fill: orange");
        lk_label.setText("Данные изменены успешно!");
        nameField.setVisible(false);
        PasswordField.setVisible(false);
        emailField.setVisible(false);
        phoneField.setVisible(false);
        change_btn.setText("На главную");

        change_btn.setOnAction(event -> {
            new HelloApplication().nextPage(change_btn.getScene().getWindow(), "auth.fxml");
        });
    }
}