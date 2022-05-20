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

import java.util.Objects;


public class LogInController {

    @FXML
    private Label login_message;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager", "Courier");
    }


    public void userLogIn(ActionEvent bookShopInterface) throws Exception {

    }

    public void createAccount(ActionEvent register)throws Exception{
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
        Stage window = (Stage) ((Node) register.getSource()).getScene().getWindow();
        window.setTitle("Registration");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

}

