package Module1;
/**
 * @author Abednego Steven - 1972009
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Paper Rock Scissors Game");
        Parent parent = FXMLLoader.load(getClass().getResource("tes.fxml"));
        primaryStage.setResizable(false); // disable maximize
        Scene scene1 = new Scene(parent,530, 450);
        primaryStage.setScene(scene1);
        //primaryStage.centerOnScreen();
        primaryStage.show();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2 );
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2 );

    }

    public static void main(String[] args) {
        launch(args);
    }
}
