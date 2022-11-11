module com.example.stackpane {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stackpane to javafx.fxml;
    exports com.example.stackpane;
}