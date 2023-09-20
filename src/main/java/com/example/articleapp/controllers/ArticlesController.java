package com.example.articleapp.controllers;

import com.example.articleapp.DB.DB_Actions;
import com.example.articleapp.HelloApplication;
import com.example.articleapp.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ArticlesController {
    @FXML
    public VBox articleBox;

    @FXML
    private Button exit_btn;

    @FXML
    private Button lk_btn;

    @FXML
    void initialize() throws IOException, SQLException {

        exit_btn.setOnMouseClicked(event -> {
            new HelloApplication().nextPage(exit_btn.getScene().getWindow(), "auth.fxml");
            User.exitUser();
        });

        lk_btn.setOnAction(event -> {
            new HelloApplication().nextPage(lk_btn.getScene().getWindow(), "lk.fxml");
        });


        ResultSet resultSet = DB_Actions.getAllArticle();
        while (resultSet.next()) {
            Node article = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("article_style.fxml")));
            articleBox.getChildren().add(article);
            Label tittle = (Label) article.lookup("#name_field");
            Label text = (Label) article.lookup("#text_field");
            tittle.setText(resultSet.getString("tittle"));
            text.setText(resultSet.getString("text").subSequence(0, 70) + "...");
            article.setOnMouseEntered(mouseEvent -> text.setStyle("-fx-background-color: orange"));
            article.setOnMouseExited(mouseEvent -> text.setStyle("-fx-background-color: white"));
            article.setOnMouseClicked(mouseEvent -> {
                ReadArticleController.article = tittle.getText();
                new HelloApplication().nextPage(articleBox.getScene().getWindow(), "read_article.fxml");
            });

        }
    }
}
