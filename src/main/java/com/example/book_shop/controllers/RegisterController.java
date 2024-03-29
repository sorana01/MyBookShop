package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.*;
import com.example.book_shop.services.UserService;
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
        try {
            UserService.addUser(username.getText(), password.getText(), (String) role.getValue(),
                    full_name.getText(), address.getText(), phone_number.getText(), email.getText(), confirm_password.getText());
            registration_message.setText("Account created successfully!");
        } catch (EmptyTextFieldsException e){
            registration_message.setText(e.getMessage());
        } catch (WrongPasswordConfirmationException e) {
            registration_message.setText(e.getMessage());
        } catch (UsernameAlreadyExistsException e) {
            registration_message.setText(e.getMessage());
        } catch (EmailAlreadyUsedException e){
            registration_message.setText(e.getMessage());
        } catch(PhoneNumberAlreadyUsedException e) {
            registration_message.setText(e.getMessage());
        } catch(NotAllCharactersAreDigitsException e) {
            registration_message.setText(e.getMessage());
        }
    }

    public void goBackToLogin(ActionEvent login) throws IOException {
        Parent backToLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Login");
        window.setScene(new Scene(backToLogin, 600, 400));
        window.show();
    }
}
