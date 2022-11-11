package com.example.stackpane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    public AnchorPane anchor2;
    public AnchorPane anchor3;
    public AnchorPane anchor1;
    public Button btn1;
    public Button btn2;
    public Button btn3;

    public void handleClicks(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btn1 ){
            anchor1.toFront();
        } else if (actionEvent.getSource() == btn2){
            anchor2.toFront();
        } else {
            anchor3.toFront();
        }
    }
}