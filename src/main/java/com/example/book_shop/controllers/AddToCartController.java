package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.BookDoesntExistException;
import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.InvalidQuantityException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.services.ClientBookService;
import com.example.book_shop.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddToCartController {
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField quantity;
    @FXML
    private Label message;


    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("client_page.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(parent, 600, 460));
        window.show();
    }

    public void addToCart(ActionEvent actionEvent) {
        try {
            ClientBookService.addABookToCart(title.getText(), author.getText(), quantity.getText());
            message.setText("Book added to Cart!");
        } catch (BookDoesntExistException e) {
            message.setText(e.getMessage());
        } catch(EmptyTextFieldsException e) {
            message.setText(e.getMessage());
        } catch(InvalidQuantityException e) {
            message.setText(e.getMessage());
        } catch (NotAllCharactersAreDigitsException e) {
            message.setText(e.getMessage());
        }
    }

    public void seeCart(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("see_cart.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Shopping Cart");
        window.setScene(new Scene(parent, 600, 460));
        window.show();
    }
}
