package com.example.book_shop;

import com.example.book_shop.services.ClientBookService;
import com.example.book_shop.services.ManagerBookService;
import com.example.book_shop.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        UserService.loadUsersFromFile();
        ManagerBookService.loadBooksFromFile();
        ClientBookService.initializeShoppingCart();
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Book Shop Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}