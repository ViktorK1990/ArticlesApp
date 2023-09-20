package com.example.articleapp;

import com.example.articleapp.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String startWindow = User.isUserExist() ? "articles.fxml" : "auth.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(startWindow));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    public void nextPage(Window btn, String window) {
        btn.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(window));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}