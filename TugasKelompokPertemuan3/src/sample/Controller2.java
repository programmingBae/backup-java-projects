package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sample.DAO.StudentDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2  {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtUAS;
    @FXML
    private TextField txtKAT;
    @FXML
    private TextField txtUTS;


    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;

    }

    public void addData(ActionEvent actionEvent) {
        Student student = new Student();
        student.setNama(txtNama.getText());
        student.setKat(Double.parseDouble(txtKAT.getText()));
        student.setUas(Double.parseDouble(txtUAS.getText()));
        student.setUts(Double.parseDouble(txtUTS.getText()));
        student.setRata();
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addData(student);
        controller.getStudents().add(student);
        txtNama.setText("");
        txtKAT.setText("");
        txtUAS.setText("");
        txtUTS.setText("");
    }


}
