package com.example.book_shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
        Parent backToLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Login");
        window.setScene(new Scene(backToLogin, 600, 400));
        window.show();
    }
}
