package com.example.book_shop.controllers;

import com.example.book_shop.exceptions.BookDoesntExistException;
import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NothingToEditException;
import com.example.book_shop.services.ManagerBookService;
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

    public void back_button(ActionEvent actionEvent) throws IOException {
        Parent adminTripPageInterface = FXMLLoader.load(getClass().getClassLoader().getResource("manager_page.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(adminTripPageInterface, 600, 460));
        window.show();
    }

    public void edit_book(ActionEvent actionEvent) {
        try {
            ManagerBookService.editBook(title.getText(), author.getText(), title_new.getText(), author_new.getText(), price_new.getText(), (String) category_new.getValue(), quantity_new.getText());
            add_message.setText("Book edited successfully!");
        } catch(BookDoesntExistException e) {
            add_message.setText(e.getMessage());
        } catch(EmptyTextFieldsException e) {
            add_message.setText(e.getMessage());
        } catch(NothingToEditException e) {
            add_message.setText(e.getMessage());
        }
    }
}
