package com.example.book_shop.controllers;

import com.example.book_shop.model.Book;
import com.example.book_shop.services.ClientBookService;
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

public class SeeCartController {
    private static List<Book> shopping_cart_database = ClientBookService.getShoppingCart_database();

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
    public void initialize() throws IOException {
        ObservableList<Book> items = FXCollections.observableArrayList();
        title_col.setCellValueFactory(new PropertyValueFactory<>("Title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("Author"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("Price"));
        category_col.setCellValueFactory(new PropertyValueFactory<>("Category"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("Quantity"));


        for (Book book : shopping_cart_database) {
            items.add(book);
            books_view.setItems(items);
        }
    }

    public void placeOrder(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("place_order.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Finish Purchase");
        window.setScene(new Scene(parent, 600, 460));
        window.show();
    }

    public void backButton(ActionEvent actionEvent) {
    }
}
