package com.example.book_shop.controllers;

import com.example.book_shop.model.Book;
import com.example.book_shop.model.Order;
import com.example.book_shop.model.User;
import com.example.book_shop.services.ClientBookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class ManagerSeeOrdersController {
    private static List<Order> order_database = ClientBookService.getOrder_database();

    @FXML
    public TableView<Order> orders_view = new TableView<>();

    @FXML
    public TableColumn<Order, User> full_name_col;
    @FXML
    public TableColumn<Order, Book> order_col;
    @FXML
    public TableColumn<Order, String> status_col;
    @FXML
    public TableColumn<Order, Integer> order_num_col;


    @FXML
    public void initialize() throws IOException {
        ObservableList<Order> items = FXCollections.observableArrayList();
        full_name_col.setCellValueFactory(new PropertyValueFactory<>("User"));
        full_name_col.setCellFactory(column -> new TableCell<Order, User>() {
            public void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null)
                    setText("");
                else
                    setText(user.getFull_name());
            }
        });

        order_col.setCellValueFactory(new PropertyValueFactory<>("Books"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("Status"));
        order_num_col.setCellValueFactory(new PropertyValueFactory<>("Order_number"));

        for (Order order : order_database) {
            items.add(order);
            orders_view.setItems(items);
        }
    }


    public void backButton(ActionEvent actionEvent) {
    }

    public void acceptOrReject(ActionEvent actionEvent) {
    }
}
