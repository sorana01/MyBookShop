package com.example.book_shop.controllers;

import com.example.book_shop.model.Book;
import com.example.book_shop.services.ManagerBookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ManagerController {
    private static List<Book> books = ManagerBookService.getBooks();

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
    public TableColumn<Book, String> quantity_col;


    @FXML
    public void initialize() {
        ObservableList<Book> items = FXCollections.observableArrayList();
        title_col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("Author"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("Price"));
        category_col.setCellValueFactory(new PropertyValueFactory<>("Category"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        for (Book book : books) {
            items.add(book);
            books_view.setItems(items);
        }
    }

    public void addBook(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("add_books.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Add Books");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }

    public void viewOrders(ActionEvent actionEvent) {
    }

    public void deleteBook(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("delete_books.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Delete Books");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }

    public void editBook(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edit_books.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Edit Books");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }
}
