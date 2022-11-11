package Module2;
/**
 * @author Abednego Steven - 1972009
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Aplikasi Penghitung Bangun Ruang");
        Parent parent = FXMLLoader.load(getClass().getResource("tes.fxml"));
        Scene scene1 = new Scene(parent,430, 207);
        primaryStage.setResizable(false);
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
