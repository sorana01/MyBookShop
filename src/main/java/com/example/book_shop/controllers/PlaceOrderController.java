package com.example.book_shop.controllers;

import com.example.book_shop.services.ClientBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderController {
    @FXML
    private Label message;
    @FXML
    private ChoiceBox card_type;
    @FXML
    public void initialize() {
        card_type.getItems().addAll("MasterCard", "PayPal", "VISA", "AmericanExpress");
    }


    public void backButton(ActionEvent actionEvent) {
    }

    public void finishOrder(ActionEvent actionEvent) throws IOException {
        try {
            ClientBookService.placeOrder();
            message.setText("Order successfully placed!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("client_page.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(parent, 600, 460));
        window.show();
    }
}
