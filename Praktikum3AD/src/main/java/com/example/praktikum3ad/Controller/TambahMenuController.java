package com.example.praktikum3ad.Controller;

import com.example.praktikum3ad.HelloApplication;
import com.example.praktikum3ad.Model.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

public class TambahMenuController {
    public TextField txtMenu;
    public TextField txtHarga;
    public ComboBox<String> comboDiscount;
    public Button btnCustom;

    private HelloController mainController;


    public void initialize(){
        comboDiscount.setItems(FXCollections.observableArrayList("0.0","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9"));
        comboDiscount.getSelectionModel().select(0);

    }
    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    public void openCustomDiscountPage(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        System.out.println(getClass().getResource("tambahDiskon.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tambahDiskon.fxml"));
        Parent root = fxmlLoader.load();
        TambahDiskon tambahDiskon = fxmlLoader.getController();
        tambahDiskon.setParentController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(txtMenu.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Supplier Management");
        newStage.show();
    }

    public void addMenu(ActionEvent actionEvent) {
        String namaMenu = txtMenu.getText();
        float harga = Float.valueOf(txtHarga.getText());
        MenuItem item;
        if (!Objects.equals(comboDiscount.getValue(), "0.0")
                && Objects.equals(btnCustom.getText(), "Custom Discount")){
            float discount = Float.parseFloat(comboDiscount.getValue());
            item = new MenuItem(namaMenu,harga,discount);
        }
        else if (Objects.equals(comboDiscount.getValue(), "0.0") && !Objects.equals(btnCustom.getText(), "Custom Discount")){
            float discount = Float.parseFloat(btnCustom.getText());
            item = new MenuItem(namaMenu,harga,discount);
        }
        else {
            item = new MenuItem(namaMenu,harga);
        }
        mainController.getMenuList().add(item);
        Comparator<MenuItem> comparator = Comparator.comparingDouble(MenuItem::getPrice);
        FXCollections.sort(mainController.getMenuList(), comparator);
        if (mainController.getMenuList().isEmpty()){
            mainController.popUp3.setVisible(false);
            mainController.popUp3.setDisable(true);
        } else{
            mainController.popUp3.setVisible(true);
            mainController.popUp3.setDisable(false);
        }
        txtMenu.getScene().getWindow().hide();


    }
}
