package com.example.book_shop.controllers;

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

public class AddBooksController {
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    public ChoiceBox category;
    @FXML
    private Label add_message;

    @FXML
    public void initialize() {
        category.getItems().addAll("Romance", "Horror", "Thriller", "Science Fiction", "Others");
    }


    public void back_button(ActionEvent actionEvent) throws IOException {
        Parent adminTripPageInterface = FXMLLoader.load(getClass().getClassLoader().getResource("manager_page.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Book Shop");
        window.setScene(new Scene(adminTripPageInterface, 600, 460));
        window.show();
    }

    public void add_book(ActionEvent actionEvent) {
        ManagerBookService.addBook(title.getText(), author.getText(), price.getText(), (String)category.getValue(), quantity.getText());
        add_message.setText("Book added successfully!");
    }
}
