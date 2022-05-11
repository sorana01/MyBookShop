module com.example.mybookshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mybookshop to javafx.fxml;
    exports com.example.mybookshop;
}