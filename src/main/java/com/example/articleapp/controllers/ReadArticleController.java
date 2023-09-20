package com.example.articleapp.controllers;

import com.example.articleapp.DB.DB_Actions;
import com.example.articleapp.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class ReadArticleController {
    public static String article;

    @FXML
    private Label text_label;

    @FXML
    private ImageView back_btn;

    @FXML
    void initialize() {
        text_label.setText(DB_Actions.getArticle(article));
        back_btn.setOnMouseClicked(mouseEvent -> {
            new HelloApplication().nextPage(back_btn.getScene().getWindow(), "articles.fxml");
        });
    }
}