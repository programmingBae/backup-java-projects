module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

    exports com.example.demo.Entities;
    exports com.example.demo.utility;
    exports com.example.demo.DAO;
}