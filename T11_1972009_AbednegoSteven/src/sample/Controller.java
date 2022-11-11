package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ComboBox<String> comboBahasa = new ComboBox<String>();

    @FXML
    private TextField txtNrp;
    @FXML
    private TextField txtFirst;
    @FXML
    private TextField txtLast;
    @FXML
    private TextField txtPhone;
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> kolomNRP;
    @FXML
    private TableColumn<Student, String> kolomFirst;
    @FXML
    private TableColumn<Student, String> kolomLast;
    @FXML
    private TableColumn<Student, String> kolomPhone;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    public void saveButton(ActionEvent actionEvent) {
        if (!txtFirst.getText().isEmpty() && !txtLast.getText().isEmpty() && !txtNrp.getText().isEmpty() &&
        !txtPhone.getText().isEmpty()){
            Student student = new Student();
            student.setFirstName(txtFirst.getText());
            student.setLastName(txtLast.getText());
            student.setNRP(Integer.valueOf(txtNrp.getText()));
            student.setPhone(txtPhone.getText());
            students.add(student);
        } else {
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        }
        txtPhone.setText("");
        txtNrp.setText("");
        txtLast.setText("");
        txtFirst.setText("");
    }

    public void setEN(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBahasa.setPromptText("Select Languages");
        ObservableList<String> list = comboBahasa.getItems();
        list.add("Default");
        list.add("English-US");
        list.add("Indonesia-IDN");
        table.setItems(students);
        kolomNRP.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getNRP())));
        kolomFirst.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getFirstName()));
        kolomLast.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getLastName()));
        kolomPhone.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPhone()));
    }

    public void LoadView(Locale locale) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        loader.setResources(ResourceBundle.getBundle("bundle", locale));
        Parent root = loader.load();
        Stage s = (Stage) comboBahasa.getScene().getWindow();
        Scene tes = new Scene(root);
        s.setScene(tes);

    }




    public void changeLanguage(ActionEvent actionEvent) throws IOException {
        if (comboBahasa.getSelectionModel().getSelectedItem().equals("English-US"))  {
            Locale l = new Locale("EN");
            LoadView(l);
        } else if (comboBahasa.getSelectionModel().getSelectedItem().equals("Indonesia-IDN")) {
            Locale l = new Locale("IN");
            LoadView(l);
        } else {
             Locale l = new Locale("EN");
             LoadView(l);
        }

    }
}
