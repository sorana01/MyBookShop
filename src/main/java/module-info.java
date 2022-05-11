module com.example.book_shop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.book_shop to javafx.fxml;
    exports com.example.book_shop;
    exports com.example.book_shop.controllers;
    opens com.example.book_shop.controllers to javafx.fxml;
}