package com.example.book_shop.controllers;

import com.example.book_shop.model.Book;
import com.example.book_shop.model.Order;
import com.example.book_shop.model.User;
import com.example.book_shop.services.ClientBookService;
import com.example.book_shop.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ClientSeeOrdersController {
    private static List<Order> order_database = ClientBookService.getOrder_database();
    String loggedUser = LogInController.getLoggedUser();

    @FXML
    public TableView<Order> orders_view = new TableView<>();

    @FXML
    public TableColumn<Order, Book> books_col;
    @FXML
    public TableColumn<Order, String> number_col;
    @FXML
    public TableColumn<Order, String> status_col;

    @FXML
    public void initialize() throws IOException {
        ObservableList<Order> items = FXCollections.observableArrayList();


        for (Order order : order_database) {
            if (Objects.equals(order.getUser().getUsername(), loggedUser)) {
                books_col.setCellValueFactory(new PropertyValueFactory<>("Books"));
                status_col.setCellValueFactory(new PropertyValueFactory<>("Status"));
                number_col.setCellValueFactory(new PropertyValueFactory<>("Order_number"));
                items.add(order);
                orders_view.setItems(items);
            }
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("client_page.fxml")));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Add Products to Cart");
        window.setScene(new Scene(root1, 600, 460));
        window.show();
    }
}
