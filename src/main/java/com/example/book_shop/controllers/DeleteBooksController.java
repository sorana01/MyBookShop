package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.BookDoesntExistException;
import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.services.ManagerBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DeleteBooksController {
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private Label add_message;


    public void delete_book(ActionEvent actionEvent) {
        ManagerBookService.deleteBook(title.getText(), author.getText());
        add_message.setText("Book deleted successfully!");
    }

    public void back_button(ActionEvent actionEvent) throws IOException {

    }
}
