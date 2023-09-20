module com.example.articleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.articleapp to javafx.fxml;
    exports com.example.articleapp;
    exports com.example.articleapp.controllers;
    opens com.example.articleapp.controllers to javafx.fxml;
}