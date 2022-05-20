package com.example.book_shop.controllers;

import com.example.book_shop.services.ManagerBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditBooksController {

    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField title_new;
    @FXML
    private TextField author_new;
    @FXML
    private TextField price_new;
    @FXML
    private TextField quantity_new;
    @FXML
    public ChoiceBox category_new;
    @FXML
    private Label add_message;

    @FXML
    public void initialize() {
        category_new.getItems().addAll("Romance", "Horror", "Thriller", "Science Fiction", "Others");
    }

    public void back_button(ActionEvent actionEvent) {
    }

    public void edit_book(ActionEvent actionEvent) {
        ManagerBookService.editBook(title.getText(), author.getText(), title_new.getText(), author_new.getText(), price_new.getText(), (String) category_new.getValue(), quantity_new.getText());
        add_message.setText("Book edited successfully!");
    }
}
