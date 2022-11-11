package com.eriko.controller;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import com.eriko.model.Anggota;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MemberController implements Initializable {
    private MainController mainController;

    @FXML
    private TableView<Anggota> tableView;
    @FXML
    private TableColumn<Anggota,Integer> col1;
    @FXML
    private TableColumn<Anggota,String> col2;
    @FXML
    private TableColumn<Anggota,String> col3;
    @FXML
    private TableColumn<Anggota, Date> col4;
    @FXML
    private TableColumn<Anggota,Date> col5;
    @FXML
    private Label labelId;
    @FXML
    private Label labelName;
    @FXML
    private Label labelDateBirth;
    @FXML
    private TextField textfieldName;
    @FXML
    private TextField textfieldId;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelDateEntry;
    @FXML
    private Button saveButton;
    @FXML
    private TextField textfieldPhone;
    @FXML
    private DatePicker textfieldDateBirth;
    @FXML
    private DatePicker textfieldDateEntry;

    @FXML
    private void OnPressedSave(ActionEvent actionEvent) {
        if(!textfieldName.getText().trim().isEmpty() && !textfieldId.getText().trim().isEmpty() && !textfieldPhone.getText().trim().isEmpty() && textfieldDateBirth.getValue() != null && textfieldDateEntry.getValue() != null ){
            try{
                int id = Integer.parseInt(textfieldId.getText());
                Anggota anggota = new Anggota();
                anggota.setId(id);
                anggota.setNama(textfieldName.getText());
                anggota.setNotelpon(textfieldPhone.getText());
                anggota.setTglLahir(Date.valueOf(textfieldDateBirth.getValue()));
                anggota.setTglMasuk(Date.valueOf(textfieldDateEntry.getValue()));
                mainController.addMember(anggota);

            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Id not numberic");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Text Field Empty");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelId.setText(resources.getString("member.id"));
        labelName.setText(resources.getString("member.name"));
        labelPhone.setText(resources.getString("member.phone"));
        labelDateBirth.setText(resources.getString("member.dateBirth"));
        labelDateEntry.setText(resources.getString("member.dateEntry"));
        col1.setText(resources.getString("member.id"));
        col2.setText(resources.getString("member.name"));
        col3.setText(resources.getString("member.phone"));
        col4.setText(resources.getString("member.dateBirth"));
        col5.setText(resources.getString("member.dateEntry"));

        col1.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        col2.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNama()));
        col3.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNotelpon()));
        col4.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getTglLahir()));
        col5.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getTglMasuk()));

    }

    public void setController(MainController controller) {
        mainController = controller;
        tableView.setItems(controller.getMembers());
    }
}
