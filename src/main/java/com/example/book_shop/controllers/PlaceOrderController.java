package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.services.ClientBookService;
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

public class PlaceOrderController {
    @FXML
    private Label message;
    @FXML
    private ChoiceBox card_type;
    @FXML
    private TextField full_name;
    @FXML
    private TextField card_number;
    @FXML
    private TextField cvv;
    @FXML
    private ChoiceBox month;
    @FXML
    private ChoiceBox year;

    int window = 0;

    @FXML
    public void initialize() {
        card_type.getItems().addAll("MasterCard", "PayPal", "VISA", "AmericanExpress");
        month.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        year.getItems().addAll("2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030");
    }



    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("see_cart.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Shopping Cart");
        window.setScene(new Scene(parent, 600, 460));
        window.show();
    }

    public void finishOrder(ActionEvent actionEvent) throws IOException {
        try {
            ClientBookService.placeOrder(full_name.getText(), (String) card_type.getValue(), card_number.getText(), cvv.getText(),
                    (String) month.getValue(), (String) year.getValue());
            message.setText("Order successfully placed!");
            window = 1;
        } catch (EmptyTextFieldsException e) {
            message.setText(e.getMessage());
        } catch (NotAllCharactersAreDigitsException e) {
            message.setText(e.getMessage());
        } catch(IOException e) {
            e.printStackTrace();
        }

        if (window == 1) {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("client_page.fxml"));
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setTitle("Book Shop");
            window.setScene(new Scene(parent, 600, 460));
            window.show();
        }
    }
}
