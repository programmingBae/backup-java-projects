package controller;

/**
 * @author - Abednego Steven - 1972009
 */

import entity.Barang;
import entity.Supplier;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtHarga;
    @FXML 
    private TextField txtNamaSupplier;
    @FXML
    private ComboBox<Supplier> comboSupplier;
    @FXML
    private TableView<Barang> tableBarang;
    @FXML
    private TableColumn<Barang, String> kolomNama;
    @FXML
    private TableColumn<Barang, String> kolomHarga;
    @FXML
    private TableColumn<Barang, String> kolomSupplier;
    @FXML
    private Button saveBarang;
    @FXML
    private Button saveSupplier;
    @FXML
    private Button reset;
    @FXML
    private Button update;
    @FXML
    private MenuItem Exit;

    private boolean barangDuplikat = false;


    private ObservableList<Barang> listBarang;
    private ObservableList<Supplier> listSupplier;





    public void save(ActionEvent actionEvent) {
        if (txtNama.getText().isEmpty() || txtHarga.getText().isEmpty() ||
                comboSupplier.getSelectionModel().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < listBarang.size();i++){
                    if (listBarang.get(i).getNama().equalsIgnoreCase(txtNama.getText())){
                        barangDuplikat = true;
                        break;
                    } else {
                        barangDuplikat = false;
                    }
                }
                Barang barang = new Barang();
                barang.setNama(txtNama.getText());
                barang.setHarga(Integer.valueOf(txtHarga.getText()));
                barang.setSupplier(comboSupplier.getValue());

                if (barangDuplikat != true){
                    listBarang.add(barang);
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Barang sudah ada di dalam table");
                    alertInformation.show();
                }
                txtNama.setText("");
                txtHarga.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for Harga");
                alertInformation.show();
            }
        }


    }

    public void reset(ActionEvent actionEvent) {
        txtHarga.setText("");
        txtNama.setText("");
        txtNamaSupplier.setText("");
    }

    public void update(ActionEvent actionEvent) {
        if (txtNama.getText().isEmpty() || txtHarga.getText().isEmpty() ||
                comboSupplier.getSelectionModel().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < listBarang.size();i++) {
                    if (listBarang.get(i).getNama().equalsIgnoreCase(txtNama.getText())) {
                        barangDuplikat = true;
                        break;
                    } else {
                        barangDuplikat = false;
                    }
                }
                if (barangDuplikat != true){
                    tableBarang.getSelectionModel().getSelectedItem().setNama(txtNama.getText());
                    tableBarang.getSelectionModel().getSelectedItem().setHarga(Integer.valueOf(txtHarga.getText()));
                    tableBarang.getSelectionModel().getSelectedItem().setSupplier(comboSupplier.getValue());
                    tableBarang.refresh();
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Barang sudah ada di dalam table");
                    alertInformation.show();
                }


                txtNama.setText("");
                txtHarga.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for Harga");
                alertInformation.show();
            }
        }
        saveBarang.setDisable(false);
        update.setDisable(true);
    }

    public void saveSupplier(ActionEvent actionEvent) {
        if (txtNamaSupplier.getText().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in the field");
            alertInformation.show();
        } else {
                Supplier supplier = new Supplier();
                supplier.setNama(txtNamaSupplier.getText());
                listSupplier.add(supplier);
                txtNamaSupplier.setText("");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listSupplier = FXCollections.observableArrayList();
        comboSupplier.setItems(listSupplier);
        listBarang = FXCollections.observableArrayList();
        tableBarang.setItems(listBarang);
        kolomNama.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getNama()));
        kolomHarga.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getHarga())));
        kolomSupplier.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getSupplier().getNama()));


    }


    public void itemsSelected(MouseEvent mouseEvent) {
        if (!tableBarang.getSelectionModel().getSelectedCells().isEmpty()){
            saveBarang.setDisable(true);
        }
        txtNama.setText(tableBarang.getSelectionModel().getSelectedItem().getNama());
        txtHarga.setText(String.valueOf(tableBarang.getSelectionModel().getSelectedItem().getHarga()));
        update.setDisable(false);
    }

    public void Exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void About(ActionEvent actionEvent) {
        Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setContentText("Created by Abednego Steven - 1972009");
        alertInformation.show();
    }
}
