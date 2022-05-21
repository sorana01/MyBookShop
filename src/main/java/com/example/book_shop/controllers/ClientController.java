package com.example.book_shop.controllers;

import com.example.book_shop.model.Book;
import com.example.book_shop.services.ManagerBookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ClientController {
    private static List<Book> book_database = ManagerBookService.getBooks();

    @FXML
    public TableView<Book> books_view = new TableView<>();

    @FXML
    public TableColumn<Book, String> title_col;
    @FXML
    public TableColumn<Book, String> author_col;
    @FXML
    public TableColumn<Book, String> price_col;
    @FXML
    public TableColumn<Book, String> category_col;


    @FXML
    public void initialize() throws IOException {
        ObservableList<Book> items = FXCollections.observableArrayList();
        title_col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("Author"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("Price"));
        category_col.setCellValueFactory(new PropertyValueFactory<>("Category"));

        for (Book book : book_database) {
            items.add(book);
            books_view.setItems(items);
        }
    }


    public void addToCart(ActionEvent actionEvent) {
    }

    public void seeOrders(ActionEvent actionEvent) {
    }
}
