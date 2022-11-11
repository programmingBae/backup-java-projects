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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    @FXML
    public BorderPane borderPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TableView<Supplier> tableSupplier;
    @FXML
    private TableColumn<Supplier,String> kolomID;
    @FXML
    private TableColumn<Supplier, String> kolomNamaSupplier;
    @FXML
    private TableColumn<Supplier, String> kolomAlamatSupplier;

    private FirstController firstController;



    private boolean supplierDuplikat;

    public void setFirstController(FirstController firstController) {
        this.firstController = firstController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kolomNamaSupplier.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNama()));
        kolomID.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        kolomAlamatSupplier.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getAlamat()));

    }

    public void saveSupplier(ActionEvent actionEvent) {
        if (txtNama.getText().trim().isEmpty() || txtID.getText().trim().isEmpty() ||
                txtAlamat.getText().trim().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < firstController.getListSupplier().size();i++){
                    if (firstController.getListSupplier().get(i).getId()==Integer.parseInt(txtID.getText())){
                        supplierDuplikat = true;
                        break;
                    } else {
                        supplierDuplikat = false;
                    }
                }
                Supplier supplier = new Supplier();
                supplier.setId(Integer.valueOf(txtID.getText()));
                supplier.setNama(txtNama.getText());
                supplier.setAlamat(txtAlamat.getText());

                    if (supplierDuplikat != true){
                    firstController.getListSupplier().add(supplier);
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Supplier dengan ID yang sama sudah ada di dalam table");
                    alertInformation.show();
                }
                txtNama.setText("");
                txtID.setText("");
                txtAlamat.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }

        }
    }

    public void resetSupplier(ActionEvent actionEvent) {
        txtID.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
    }

    public void updateSupplier(ActionEvent actionEvent) {
        if (txtNama.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty() ||
                txtID.getText().trim().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < firstController.getListSupplier().size();i++) {
                    if (firstController.getListSupplier().get(i).getId() == Integer.parseInt(txtID.getText())
                    && firstController.getListSupplier().get(i).getId() != Integer.valueOf(txtID.getText())) {
                        supplierDuplikat = true;
                        break;
                    } else {
                        supplierDuplikat = false;
                    }
                }
                if (supplierDuplikat != true){
                    tableSupplier.getSelectionModel().getSelectedItem().setNama(txtNama.getText());
                    tableSupplier.getSelectionModel().getSelectedItem().setId(Integer.valueOf(txtID.getText()));
                    tableSupplier.getSelectionModel().getSelectedItem().setAlamat(txtAlamat.getText());
                    tableSupplier.refresh();
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Supplier dengan ID yang sama sudah ada di dalam table");
                    alertInformation.show();
                }


                txtNama.setText("");
                txtAlamat.setText("");
                txtID.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for Harga");
                alertInformation.show();
            }
        }
        saveButton.setDisable(false);
        updateButton.setDisable(true);
        txtID.setDisable(false);
    }

    public void supplierSelected(MouseEvent mouseEvent) {
        if (!tableSupplier.getSelectionModel().getSelectedCells().isEmpty()){
            saveButton.setDisable(true);
        }
        txtID.setDisable(true);
        txtID.setText(String.valueOf(tableSupplier.getSelectionModel().getSelectedItem().getId()));
        txtNama.setText(tableSupplier.getSelectionModel().getSelectedItem().getNama());
        txtAlamat.setText(String.valueOf(tableSupplier.getSelectionModel().getSelectedItem().getAlamat()));
        updateButton.setDisable(false);
    }

    public void about(ActionEvent actionEvent) {
        Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setContentText("Created by Abednego Steven - 1972009");
        alertInformation.show();
    }


    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }
}
