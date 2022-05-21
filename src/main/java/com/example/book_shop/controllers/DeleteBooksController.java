package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.BookDoesntExistException;
import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.services.ManagerBookService;
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

public class DeleteBooksController {
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private Label add_message;


    public void delete_book(ActionEvent actionEvent) {
        try {
            ManagerBookService.deleteBook(title.getText(), author.getText());
            add_message.setText("Book deleted successfully!");
        } catch(BookDoesntExistException e) {
            add_message.setText(e.getMessage());
        } catch(EmptyTextFieldsException e) {
            add_message.setText(e.getMessage());
        }
    }

    public void back_button(ActionEvent actionEvent) throws IOException {
        Parent adminTripPageInterface = FXMLLoader.load(getClass().getClassLoader().getResource("manager_page.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(adminTripPageInterface, 600, 460));
        window.show();
    }
}
