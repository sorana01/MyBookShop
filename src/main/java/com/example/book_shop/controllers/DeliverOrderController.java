package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.exceptions.OrderNumberDoesntExistException;
import com.example.book_shop.exceptions.StatusAlreadyModifiedException;
import com.example.book_shop.services.CourierBookService;
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
        try{
            CourierBookService.modifyOrderStatus(order_number.getText(), (String) new_status.getValue());
            message.setText("Status modified successfully!");
        } catch(NotAllCharactersAreDigitsException e) {
            message.setText(e.getMessage());
        } catch(EmptyTextFieldsException e) {
            message.setText(e.getMessage());
        } catch(StatusAlreadyModifiedException e) {
            message.setText(e.getMessage());
        } catch(OrderNumberDoesntExistException e) {
            message.setText(e.getMessage());
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("courier_page.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }
}
