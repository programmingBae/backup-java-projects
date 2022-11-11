package com.example.praktikum3ad.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class TambahDiskon {
    public TextField txtDiskon;

    private TambahMenuController parentController;

    public void setParentController(TambahMenuController parentController) {
        this.parentController = parentController;
    }

    public void addDiscount(ActionEvent actionEvent) {
        String diskon = txtDiskon.getText();
        parentController.btnCustom.setText(diskon);
        txtDiskon.getScene().getWindow().hide();
    }
}
