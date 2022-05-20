package com.example.book_shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBooksController {
    public void back_button(ActionEvent actionEvent) throws IOException {
        Parent adminTripPageInterface = FXMLLoader.load(getClass().getClassLoader().getResource("manager_page.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(adminTripPageInterface, 600, 460));
        window.show();
    }

    public void add_book(ActionEvent actionEvent) {
    }
}
