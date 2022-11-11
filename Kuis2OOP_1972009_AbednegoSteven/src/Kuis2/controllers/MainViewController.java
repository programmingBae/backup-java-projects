package Kuis2.controllers;

/**
 * @author AbednegoSteven-1972009
 */
import Kuis2.dao.AnggotaDaoImpl;
import Kuis2.dao.BukuDaoImpl;
import Kuis2.dao.PeminjamanDaoImpl;
import Kuis2.entities.AnggotaEntity;
import Kuis2.entities.BukuEntity;
import Kuis2.entities.PeminjamanEntity;
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;


public class MainViewController implements Initializable {
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnReset;
    @FXML
    public TableView<PeminjamanEntity> tableView;
    @FXML
    public TableColumn<PeminjamanEntity, String> col1;
    @FXML
    public TableColumn<PeminjamanEntity, String> col2;
    @FXML
    public TableColumn<PeminjamanEntity, String> col3;
    @FXML
    public TableColumn<PeminjamanEntity, String> col4;
    @FXML
    public TableColumn<PeminjamanEntity, String> col5;
    @FXML
    public ComboBox<AnggotaEntity> comboMember;
    @FXML
    public TextField txtId;
    private String bahasa[] = {"Default","English-US","Indonesia-IDN"};
    @FXML
    public ComboBox<String> comboBahasa;
    @FXML
    public ComboBox<BukuEntity> comboBook;
    @FXML
    public DatePicker dateBorrow;
    @FXML
    public DatePicker dateReturn;

    private ObservableList<AnggotaEntity> anggotaEntities = FXCollections.observableArrayList();
    private ObservableList<BukuEntity> bookEntities = FXCollections.observableArrayList();
    private ObservableList<PeminjamanEntity> peminjamanEntities = FXCollections.observableArrayList();

    private AnggotaDaoImpl anggotaDao = new AnggotaDaoImpl();
    private BukuDaoImpl bukuDao = new BukuDaoImpl();
    private PeminjamanDaoImpl peminjamanDao = new PeminjamanDaoImpl();
    private PeminjamanEntity peminjamanEntity2;
    private String locale2 = "EN";
    private ArrayList<String> tes = new ArrayList<String>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBahasa.setItems(FXCollections.observableArrayList(bahasa));

        anggotaEntities = (ObservableList<AnggotaEntity>) anggotaDao.getAll();
        bookEntities = (ObservableList<BukuEntity>) bukuDao.getAll();
        peminjamanEntities = (ObservableList<PeminjamanEntity>) peminjamanDao.getAll();

        comboMember.setItems(anggotaEntities);
        comboBook.setItems(bookEntities);
        tableView.setItems(peminjamanEntities);
        col1.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        col2.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAnggotaByAnggotaId().getNama()));
        col3.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getBukuByBukuId().getJudul()));
        col4.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTanggalPinjam())));
        col5.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTanggalPengembalian())));
    }

    public void exitProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void openBookManagement(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        ResourceBundle resources = ResourceBundle.getBundle("resourceBundle", new Locale(locale2));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/bookManagement.fxml"));
        loader.setResources(resources);
        Parent root = loader.load();
        BookManagementController controller2 = loader.getController();
        controller2.setMainViewController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(btnDelete.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Book Management");
        newStage.show();
    }

    public void openMemberManagement(ActionEvent actionEvent) throws IOException {
        System.out.println(locale2);
        Stage newStage = new Stage();
        if (comboBahasa.getValue().equals("Indonesia-IDN")){
            Locale l = new Locale("IN");
            ResourceBundle resources = ResourceBundle.getBundle("resourceBundle", l);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/MemberManagement.fxml"));
            loader.setResources(resources);
            Parent root = loader.load();
            MemberManagementController controller2 = loader.getController();
            controller2.setMainViewController(this);
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.initOwner(btnDelete.getScene().getWindow());
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.setTitle("Member Management");
            newStage.show();
        } else {
            Locale l = new Locale("EN");
            ResourceBundle resources = ResourceBundle.getBundle("resourceBundle", l);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/MemberManagement.fxml"),resources);
            Parent root = loader.load();
            MemberManagementController controller2 = loader.getController();
            controller2.setMainViewController(this);
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.initOwner(btnDelete.getScene().getWindow());
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.setTitle("Member Management");
            newStage.show();
        }

    }

    public void saveData(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty() || dateBorrow.getValue()==null || dateReturn.getValue()==null
        || comboMember.getValue()==null || comboBook.getValue()==null){
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                PeminjamanEntity peminjamanEntity = new PeminjamanEntity();
                peminjamanEntity.setId(Integer.parseInt(txtId.getText()));
                peminjamanEntity.setTanggalPinjam(Date.valueOf(dateBorrow.getValue()));
                peminjamanEntity.setTanggalPengembalian(Date.valueOf(dateReturn.getValue()));
                peminjamanEntity.setAnggotaByAnggotaId(comboMember.getValue());
                peminjamanEntity.setBukuByBukuId(comboBook.getValue());
                peminjamanDao.addData(peminjamanEntity);
                peminjamanEntities.clear();
                peminjamanEntities.addAll(peminjamanDao.getAll());
                txtId.setText("");
                dateReturn.setValue(null);
                dateBorrow.setValue(null);
                comboBook.setValue(null);
                comboMember.setValue(null);
            } catch (NumberFormatException ex){
                Alert alertInformation = new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }
        }
    }


    public void updateData(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty() || dateBorrow.getValue()==null || dateReturn.getValue()==null
                || comboMember.getValue()==null || comboBook.getValue()==null){
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                PeminjamanEntity peminjamanEntity = peminjamanEntity2;
                peminjamanEntity.setId(Integer.parseInt(txtId.getText()));
                peminjamanEntity.setTanggalPinjam(Date.valueOf(dateBorrow.getValue()));
                peminjamanEntity.setTanggalPengembalian(Date.valueOf(dateReturn.getValue()));
                peminjamanEntity.setAnggotaByAnggotaId(comboMember.getValue());
                peminjamanEntity.setBukuByBukuId(comboBook.getValue());
                peminjamanDao.updateData(peminjamanEntity);
                peminjamanEntities.clear();
                peminjamanEntities.addAll(peminjamanDao.getAll());
                txtId.setText("");
                dateReturn.setValue(null);
                dateBorrow.setValue(null);
                comboBook.setValue(null);
                comboMember.setValue(null);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
                btnSave.setDisable(false);
                txtId.setDisable(false);
            } catch (NumberFormatException ex){
                Alert alertInformation = new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }
        }
    }

    public void deleteData(ActionEvent actionEvent) {
        peminjamanDao.delData(peminjamanEntity2);
        peminjamanEntities.clear();
        peminjamanEntities.addAll(peminjamanDao.getAll());
        txtId.setText("");
        dateReturn.setValue(null);
        dateBorrow.setValue(null);
        comboBook.setValue(null);
        comboMember.setValue(null);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
        txtId.setDisable(false);
    }

    public void reset(ActionEvent actionEvent) {
        txtId.setText("");
        dateReturn.setValue(null);
        dateBorrow.setValue(null);
        comboBook.setValue(null);
        comboMember.setValue(null);
    }


    public void selectedItem(MouseEvent mouseEvent) {
        if (!tableView.getSelectionModel().getSelectedCells().isEmpty()) {
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            txtId.setDisable(true);
            btnSave.setDisable(true);
        }
        PeminjamanEntity peminjamanEntity;
        peminjamanEntity =tableView.getSelectionModel().getSelectedItem();
        peminjamanEntity2 = peminjamanEntity;
        txtId.setText(String.valueOf(peminjamanEntity.getId()));
        dateBorrow.setValue(peminjamanEntity.getTanggalPinjam().toLocalDate());
        dateReturn.setValue(peminjamanEntity.getTanggalPengembalian().toLocalDate());
        comboMember.setValue(peminjamanEntity.getAnggotaByAnggotaId());
        comboBook.setValue(peminjamanEntity.getBukuByBukuId());
    }

    public void LoadView(Locale locale) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/mainView.fxml"));
        loader.setResources(ResourceBundle.getBundle("resourceBundle", locale));
        Parent root = loader.load();
        Stage s = (Stage) comboBahasa.getScene().getWindow();
        Scene tes = new Scene(root);
        s.setScene(tes);
    }

    public void changeLanguage(ActionEvent actionEvent) throws IOException {
        if (comboBahasa.getSelectionModel().getSelectedItem().equals("English-US"))  {
            Locale l = new Locale("EN");
            locale2 = locale2.replace("IN","EN");
            tes.add("English-US");

            LoadView(l);
        } else if (comboBahasa.getSelectionModel().getSelectedItem().equals("Indonesia-IDN")) {
            Locale l = new Locale("IN");
            LoadView(l);
            tes.clear();
            tes.add("Indonesia-IDN");
            locale2 = locale2.replace("EN","IN");
        } else {
            Locale l = new Locale("EN");
            LoadView(l);
        }
    }


}
