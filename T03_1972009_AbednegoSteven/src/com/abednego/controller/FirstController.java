package com.abednego.controller;

/**
 * @author - AbednegoSteven - 1972009
 */


import com.abednego.entity.Barang;
import com.abednego.entity.Supplier;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstController implements Initializable {
    @FXML
    public Button updateButton;
    @FXML
    public Button saveButton;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNama;
    @FXML
    private ComboBox<Supplier> comboSupplier;
    @FXML
    private TableView<Barang> tableBarang;
    @FXML
    private TableColumn<Barang, String> kolomID;
    @FXML
    private TableColumn<Barang,String> kolomNama;
    @FXML
    private TableColumn<Barang,String> kolomSupplier;
    @FXML
    private BorderPane borderPane;

    private ObservableList<Barang> listBarang;
    private ObservableList<Supplier> listSupplier;

    private boolean barangDuplikat;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listSupplier = FXCollections.observableArrayList();
        listBarang = FXCollections.observableArrayList();
        comboSupplier.setItems(listSupplier);
        tableBarang.setItems(listBarang);
        kolomNama.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNama()));
        kolomID.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        kolomSupplier.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getSupplier().getNama()));
    }

    public void openSupplierManagement(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/second_layout.fxml"));
        Parent root = loader.load();
        SecondController secondController = loader.getController();
        secondController.setFirstController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(borderPane.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Supplier Management");
        newStage.show();

    }

    public ObservableList<Supplier> getListSupplier() {
        return listSupplier;
    }


    public void save(ActionEvent actionEvent) {
        if (txtNama.getText().trim().isEmpty() || txtID.getText().trim().isEmpty() ||
                comboSupplier.getSelectionModel().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < listBarang.size();i++){
                    if (listBarang.get(i).getId()==Integer.parseInt(txtID.getText())){
                        barangDuplikat = true;
                        break;
                    } else {
                        barangDuplikat = false;
                    }
                }
                Barang barang = new Barang();
                barang.setNama(txtNama.getText());
                barang.setId(Integer.valueOf(txtID.getText()));
                barang.setSupplier(comboSupplier.getValue());

                if (barangDuplikat != true){
                    listBarang.add(barang);
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Barang dengan ID yang sama sudah ada di dalam table");
                    alertInformation.show();
                }
                txtNama.setText("");
                txtID.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }
        }
    }

    public void reset(ActionEvent actionEvent) {
        txtID.setText("");
        txtNama.setText("");
    }

    public void update(ActionEvent actionEvent) {
        if (txtNama.getText().trim().isEmpty() || txtID.getText().trim().isEmpty() ||
                comboSupplier.getSelectionModel().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < listBarang.size();i++){
                    if (listBarang.get(i).getId()==Integer.parseInt(txtID.getText())
                            && listBarang.get(i).getId() != Integer.valueOf(txtID.getText())){
                        barangDuplikat = true;
                        break;
                    } else {
                        barangDuplikat = false;
                    }
                }
                Barang barang = new Barang();
                barang.setNama(txtNama.getText());
                barang.setId(Integer.valueOf(txtID.getText()));
                barang.setSupplier(comboSupplier.getValue());

                if (barangDuplikat != true){
                    tableBarang.getSelectionModel().getSelectedItem().setNama(txtNama.getText());
                    tableBarang.getSelectionModel().getSelectedItem().setId(Integer.valueOf(txtID.getText()));
                    tableBarang.getSelectionModel().getSelectedItem().setSupplier(comboSupplier.getValue());
                    tableBarang.refresh();
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Barang dengan ID yang sama sudah ada di dalam table");
                    alertInformation.show();
                }
                txtNama.setText("");
                txtID.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }

        }
        saveButton.setDisable(false);
        updateButton.setDisable(true);
        txtID.setDisable(false);
    }


    public void setListSupplier(ObservableList<Supplier> listSupplier) {
        this.listSupplier = listSupplier;
    }

    public void barangSelected(MouseEvent mouseEvent) {
        if (!tableBarang.getSelectionModel().getSelectedCells().isEmpty()){
            saveButton.setDisable(true);
        }
        txtID.setDisable(true);
        txtID.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItem().getId()));
        txtNama.setText(tableBarang.getSelectionModel().getSelectedItem().getNama());
        updateButton.setDisable(false);
    }

    public void about(ActionEvent actionEvent) {
        Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setContentText("Created by Abednego Steven - 1972009");
        alertInformation.show();
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }
}
