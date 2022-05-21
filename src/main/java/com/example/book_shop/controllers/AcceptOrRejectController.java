package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.exceptions.OrderNumberDoesntExistException;
import com.example.book_shop.exceptions.StatusAlreadyModifiedException;
import com.example.book_shop.services.ManagerBookService;
import com.example.book_shop.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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


    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("manager_view_orders.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }

    public void saveButton(ActionEvent actionEvent) {
        try {
            ManagerBookService.modifyOrderStatus(order_number.getText(), (String) accept_or_reject.getValue());
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
