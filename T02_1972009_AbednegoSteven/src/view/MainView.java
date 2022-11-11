package view;

/**
 * @author - Abednego Steven - 1972009
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Praktikum Minggu #2");
        Parent parent = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setResizable(false); // disable maximize
        Scene scene1 = new Scene(parent);
        primaryStage.setScene(scene1);
        //primaryStage.centerOnScreen();
        primaryStage.show();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2 );
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2 );
    }
}
