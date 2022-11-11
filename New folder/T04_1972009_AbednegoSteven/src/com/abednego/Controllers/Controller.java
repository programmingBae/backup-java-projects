package com.abednego.Controllers;

/**
 * @author AbednegoSteven-1972009
 */

import com.abednego.DAO.CategoryDAO;
import com.abednego.DAO.ItemsDAO;
import com.abednego.entity.Category;
import com.abednego.entity.Items;
import com.mysql.cj.xdevapi.Table;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextArea txtDesc;
    @FXML
    private ComboBox comboCategory;
    @FXML
    private TableView<Items> tableItem;
    @FXML
    private TableColumn<Items,String> kolomID;
    @FXML
    private TableColumn<Items,String> kolomName;
    @FXML
    private TableColumn<Items,String> kolomPrice;
    @FXML
    private TableColumn<Items,String> kolomCategory;
    @FXML
    private BorderPane borderPane;

    private ObservableList<Items> items;
    private ObservableList<Category> categories;
    private boolean barangDuplikat;

    public ObservableList<Category> getCategories() {
        return categories;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ItemsDAO itemsDAO = new ItemsDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        categories = (ObservableList<Category>) categoryDAO.showData();
        items = (ObservableList<Items>) itemsDAO.showData();
        comboCategory.setItems(categories);
        tableItem.setItems(items);
        kolomID.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        kolomName.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getName()));
        kolomPrice.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getPrice())));
        kolomCategory.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getCategory().getName()));
        editableCols();
    }

    public void saveButton(ActionEvent actionEvent) {


        if (txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() ||
                txtDesc.getText().trim().isEmpty() ||   comboCategory.getSelectionModel().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < items.size();i++){
                    if (items.get(i).getId()==Integer.parseInt(txtID.getText())){
                        barangDuplikat = true;
                        break;
                    } else {
                        barangDuplikat = false;
                    }
                }
                Items item = new Items();
                item.setId(Integer.valueOf(txtID.getText()));
                item.setPrice(Double.valueOf(txtPrice.getText()));
                item.setName(txtName.getText());
                item.setDescription(txtDesc.getText());
                item.setCategory((Category) comboCategory.getValue());

                if (barangDuplikat != true){
                    ItemsDAO itemsDAO = new ItemsDAO();
                    itemsDAO.addData(item);
                    items.clear();
                    items.addAll(itemsDAO.showData());
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Barang dengan ID yang sama sudah ada di dalam table");
                    alertInformation.show();
                }
                txtID.setText("");
                txtName.setText("");
                txtDesc.setText("");
                txtPrice.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID and Price");
                alertInformation.show();
            }
        }
    }

    public void resetButton(ActionEvent actionEvent) {
        txtID.setText("");
        txtName.setText("");
        txtDesc.setText("");
        txtPrice.setText("");
    }

    public void openNewStage(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout2.fxml"));
        Parent root = loader.load();
        Controller2 controller2 =loader.getController();
        controller2.setController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(borderPane.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Category Management");
        newStage.show();
    }

    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void about(ActionEvent actionEvent) {
        Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
        alertInformation.setContentText("Created by Abednego Steven - 1972009");
        alertInformation.show();
    }
    
    private void editableCols(){
        kolomID.setCellFactory(TextFieldTableCell.forTableColumn());
       kolomName.setCellFactory(TextFieldTableCell.forTableColumn());
        kolomName.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
            Items selected;
            selected = (Items) tableItem.getSelectionModel().getSelectedItem();
            selected.setDescription(selected.getDescription());
            selected.setName(e.getNewValue());
            selected.setPrice(selected.getPrice());
            selected.setDescription(selected.getDescription());
            selected.setCategory(selected.getCategory());
            ItemsDAO itemsDAO = new ItemsDAO();
            int result =itemsDAO.updateData(selected);
            items.clear();
            items.addAll(itemsDAO.showData());
        });
        tableItem.setEditable(true);
    }
}
