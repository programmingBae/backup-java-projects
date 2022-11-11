module com.example.teori3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.teori3 to javafx.fxml;
    exports com.example.teori3;

    opens com.example.teori3.Controller to javafx.fxml;
    exports com.example.teori3.Controller;
    opens com.example.teori3.DAO to javafx.fxml;
    exports com.example.teori3.DAO;
    opens com.example.teori3.Util to javafx.fxml;
    exports com.example.teori3.Util;
    opens com.example.teori3.Model to javafx.fxml;
    exports com.example.teori3.Model;
}