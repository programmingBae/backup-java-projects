module com.example.praktikum3ad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.praktikum3ad to javafx.fxml;
    exports com.example.praktikum3ad;
    exports com.example.praktikum3ad.Controller;
    opens com.example.praktikum3ad.Controller to javafx.fxml;
}