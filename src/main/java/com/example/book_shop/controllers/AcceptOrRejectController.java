package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.exceptions.OrderNumberDoesntExistException;
import com.example.book_shop.exceptions.StatusAlreadyModifiedException;
import com.example.book_shop.services.ManagerBookService;
import com.example.book_shop.services.UserService;
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
        try {
            UserService.checkAllDigitsEntered(order_number.getText());
            ManagerBookService.modifyOrderStatus(Integer.parseInt(order_number.getText()), (String) accept_or_reject.getValue());
            message.setText("Status modified successfully!");
        } catch (EmptyTextFieldsException e) {
            message.setText(e.getMessage());
        } catch(StatusAlreadyModifiedException e) {
            message.setText(e.getMessage());
        } catch(OrderNumberDoesntExistException e) {
            message.setText(e.getMessage());
        } catch (NotAllCharactersAreDigitsException e) {
            message.setText(e.getMessage());
        }
    }
}
