package com.example.book_shop.controllers;

import com.example.book_shop.services.CourierBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeliverOrderController {
    @FXML
    private TextField order_number;
    @FXML
    private Label message;
    @FXML
    private ChoiceBox new_status;

    public void initialize() {
        new_status.getItems().addAll("DELIVERED");
    }


    public void saveButton(ActionEvent actionEvent) {
        CourierBookService.modifyOrderStatus(Integer.parseInt(order_number.getText()), (String) new_status.getValue());
        message.setText("Status modified successfully!");
    }

    public void backButton(ActionEvent actionEvent) {
    }
}
