package Kuis2.controllers;

/**
 * @author AbednegoSteven-1972009
 */
import Kuis2.dao.AnggotaDaoImpl;
import Kuis2.entities.AnggotaEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MemberManagementController implements Initializable {
    @FXML
    public TableView<AnggotaEntity> tableView;
    @FXML
    public TableColumn<AnggotaEntity,String> col1;
    @FXML
    public TableColumn<AnggotaEntity,String> col2;
    @FXML
    public TableColumn<AnggotaEntity,String> col3;
    @FXML
    public TableColumn<AnggotaEntity,String> col4;
    @FXML
    public TableColumn<AnggotaEntity,String> col5;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtPhone;
    @FXML
    public DatePicker datePickerBirth;
    @FXML
    public DatePicker datePickerEntry;

    private ObservableList<AnggotaEntity> anggotaEntities = FXCollections.observableArrayList();
    private MainViewController mainViewController;
    private AnggotaDaoImpl anggotaDao = new AnggotaDaoImpl();

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anggotaEntities = (ObservableList<AnggotaEntity>) anggotaDao.getAll();
        tableView.setItems(anggotaEntities);
        col1.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        col2.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNama()));
        col3.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNotelpon()));
        col4.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTglLahir())));
        col5.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTglMasuk())));
    }

    public void saveData(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtPhone.getText().isEmpty() ||
        datePickerBirth.getValue() == null || datePickerEntry.getValue() == null){
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                AnggotaEntity anggotaEntity = new AnggotaEntity();
                anggotaEntity.setId(Integer.parseInt(txtId.getText()));
                anggotaEntity.setNama(txtName.getText());
                anggotaEntity.setNotelpon(txtPhone.getText());
                anggotaEntity.setAlamat("");
                anggotaEntity.setTglLahir(Date.valueOf(datePickerBirth.getValue()));
                anggotaEntity.setTglMasuk(Date.valueOf(datePickerEntry.getValue()));
                anggotaDao.addData(anggotaEntity);
                anggotaEntities.clear();
                anggotaEntities.addAll(anggotaDao.getAll());
                txtId.setText("");
                txtName.setText("");
                txtPhone.setText("");
                datePickerEntry.setValue(null);
                datePickerBirth.setValue(null);
            } catch (NumberFormatException ex) {
                Alert alertInformation = new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }
        }
    }
}
