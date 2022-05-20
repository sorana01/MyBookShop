package com.example.book_shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField username;
    @FXML
    private TextField full_name;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private TextField phone_number;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm_password;
    @FXML
    private Label registration_message;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager", "Courier");
    }



    public void createAccount(ActionEvent actionEvent) {

    }

    public void goBackToLogin(ActionEvent login) throws IOException {

    }
}
