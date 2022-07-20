module com.example.modalityprak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.modalityprak to javafx.fxml;
    exports com.example.modalityprak;
    exports controller;
    exports model;
    opens controller to javafx.fxml;
}