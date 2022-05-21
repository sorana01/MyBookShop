package com.example.book_shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CourierSeeOrdersController {
    public void deliverOrder(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("deliver_order.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("View Orders");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }
}
