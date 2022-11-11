package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtUAS;
    @FXML
    private TextField txtUTS;
    @FXML
    private TextField txtKAT;
    @FXML
    private Button submit;
    @FXML
    private TableView<Student> table1;
    @FXML
    private TableColumn<Student,String> namaSiswa;
    @FXML
    private TableColumn<Student,String> kat;
    @FXML
    private TableColumn<Student,String> uts;
    @FXML
    private TableColumn<Student,String> uas;
    @FXML
    private TableColumn<Student,String> rataRata;

    private ObservableList<Student> students;



    public void submit(ActionEvent actionEvent) {
        Student student = new Student();
        student.setNama(txtNama.getText());
        student.setNilaiKAT(Double.parseDouble(txtKAT.getText()));
        student.setNilaiUAS(Double.parseDouble(txtUAS.getText()));
        student.setNilaiUTS(Double.parseDouble(txtUTS.getText()));
        students.add(student);
        txtNama.setText("");
        txtKAT.setText("");
        txtUAS.setText("");
        txtUTS.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        students = FXCollections.observableArrayList();
        table1.setItems(students);
        namaSiswa.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNama()));
        kat.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getStringKAT()));
        uas.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getStringUAS()));
        uts.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getStringUTS()));
        rataRata.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getStringRata()));

    }
}
