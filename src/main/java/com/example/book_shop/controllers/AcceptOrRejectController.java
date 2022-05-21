package com.example.book_shop.controllers;

import com.example.book_shop.services.ManagerBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AcceptOrRejectController {
    @FXML
    Label message;
    @FXML
    TextField order_number;
    @FXML
    ChoiceBox accept_or_reject;

    @FXML
    public void initialize() {
        accept_or_reject.getItems().addAll("REJECTED", "ACCEPTED");
    }


    public void backButton(ActionEvent actionEvent) {
    }

    public void saveButton(ActionEvent actionEvent) {
        ManagerBookService.modifyOrderStatus(Integer.parseInt(order_number.getText()), (String) accept_or_reject.getValue());
    }
}
