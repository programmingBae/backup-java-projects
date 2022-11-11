package com.eriko;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ui/mainView.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundle"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("kuis #2");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
