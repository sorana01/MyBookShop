package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.UsernameDoesNotExistException;
import com.example.book_shop.exceptions.WrongPasswordException;
import com.example.book_shop.exceptions.WrongRoleException;
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

    private static String loggedUser;


    public void userLogIn(ActionEvent bookShopInterface) throws Exception {
        try {
            UserService.checkUserCredentials(username.getText(), password.getText(), (String) role.getValue());
            login_message.setText("Login successfully!");
            String userRole = UserService.getUserRole(username.getText());
            if(userRole.equals("Manager")){
                Parent adminInterface = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("manager_page.fxml")));
                Stage window = (Stage) ((Node) bookShopInterface.getSource()).getScene().getWindow();
                window.setTitle("Book Shop");
                window.setScene(new Scene(adminInterface, 600, 460));
                window.show();
            }
            else if (userRole.equals("Client")){
                Parent customerInterface = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("client_page.fxml")));
                Stage window = (Stage) ((Node) bookShopInterface.getSource()).getScene().getWindow();
                window.setTitle("Book Shop");
                window.setScene(new Scene(customerInterface, 600, 460));
                window.show();

            }
            else {
                Parent customerInterface = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("courier_page.fxml")));
                Stage window = (Stage) ((Node) bookShopInterface.getSource()).getScene().getWindow();
                window.setTitle("Book Shop");
                window.setScene(new Scene(customerInterface, 600, 460));
                window.show();
            }
        } catch(UsernameDoesNotExistException e) {
            login_message.setText(e.getMessage());
        } catch(WrongPasswordException e) {
            login_message.setText(e.getMessage());
        } catch(WrongRoleException e) {
            login_message.setText(e.getMessage());
        }
        catch(EmptyTextFieldsException e) {
            login_message.setText(e.getMessage());
        }
    }

    public void createAccount(ActionEvent register)throws Exception{
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
        Stage window = (Stage) ((Node) register.getSource()).getScene().getWindow();
        window.setTitle("Registration");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

}

