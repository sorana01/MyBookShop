module com.example.book_shop {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.databind;


    opens com.example.book_shop to javafx.fxml;
    opens com.example.book_shop.model to javafx.base;
    exports com.example.book_shop;
    exports com.example.book_shop.controllers;
    exports com.example.book_shop.model to com.fasterxml.jackson.databind;
    opens com.example.book_shop.controllers to javafx.fxml;
}