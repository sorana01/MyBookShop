package com.example.book_shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ManagerController {
    public void addBook(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("add_books.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Add Books");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }

    public void viewOrders(ActionEvent actionEvent) {
    }

    public void deleteBook(ActionEvent actionEvent) {
    }

    public void editBook(ActionEvent actionEvent) {
    }
}
