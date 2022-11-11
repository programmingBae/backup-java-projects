package com.example.praktikum3ad.Controller;

import com.example.praktikum3ad.HelloApplication;
import com.example.praktikum3ad.Model.MenuItem;
import com.example.praktikum3ad.Model.PriceComparator;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class HelloController {
    @FXML
    public ListView listMenu;
    public ContextMenu context;
    public javafx.scene.control.MenuItem popUp3;

    private ObservableList<MenuItem> menuList;


    public void initialize() {
        menuList = FXCollections.observableArrayList();
        listMenu.setItems(menuList);
        if (menuList.isEmpty()){
            popUp3.setVisible(false);
            popUp3.setDisable(true);
        } else{
            popUp3.setVisible(true);
            popUp3.setDisable(false);
        }
//        listMenu.setCellFactory(lv -> {
//            ListCell<String> cell = new ListCell<>();
//
//
//            ContextMenu contextMenu = new ContextMenu();
//            javafx.scene.control.MenuItem deleteItem = new javafx.scene.control.MenuItem();
//
//
//            deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
//            deleteItem.setOnAction(event -> listMenu.getItems().remove(cell.getItem()));
//            contextMenu.getItems().addAll(deleteItem);
//
//
//
//            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
//                if (isNowEmpty) {
//                    cell.setContextMenu(null);
//                } else {
//                    cell.setContextMenu(contextMenu);
//                    cell.setText(String.valueOf(cell.itemProperty().getValue()));
//
//
//
//                }
//            });
//
//            return cell ;
//        });

    }


    public ObservableList<MenuItem> getMenuList() {
        return menuList;
    }

    public void openAddMenu(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        System.out.println(getClass().getResource("tambahMenu.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tambahMenu.fxml"));
        Parent root = fxmlLoader.load();
        TambahMenuController secondController = fxmlLoader.getController();
        secondController.setMainController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(listMenu.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Supplier Management");
        newStage.show();
    }

    public void Remove(ActionEvent actionEvent) {
        menuList.remove(listMenu.getSelectionModel().getSelectedIndex());
        if (menuList.isEmpty()){
            popUp3.setVisible(false);
            popUp3.setDisable(true);
        } else{
            popUp3.setVisible(true);
            popUp3.setDisable(false);
        }
    }
}