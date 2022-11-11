import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Controller1 {

    @FXML
    private Label label1;
    @FXML
    private Button button1;
    @FXML
    private Button foto1;
    @FXML
    private Button foto2;
    @FXML
    private ImageView image;
    @FXML
    private ImageView image2;





    public void buttonAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void foto1 (ActionEvent actionEvent) {
        Image imageFoto1 = new Image("anime1.jpg");
        image.setImage(imageFoto1);
        image.setFitHeight(500);
        image.setFitWidth(300);
        Image imageFoto2 = new Image("anime2.jpg");
        image2.setImage(imageFoto2);
        image2.setFitHeight(500);
        image2.setFitWidth(300);
    }

    public void foto2 (ActionEvent actionEvent) {
        Image imageFoto1 = new Image("anime3.jpg");
        image.setImage(imageFoto1);
        image.setFitHeight(500);
        image.setFitWidth(300);
        Image imageFoto2 = new Image("anime4.jpg");
        image2.setImage(imageFoto2);
        image2.setFitHeight(500);
        image2.setFitWidth(300);
    }

    public void foto3(ActionEvent actionEvent) {
        Image imageFoto1 = new Image("anime5.jpg");
        image.setImage(imageFoto1);
        image.setFitHeight(500);
        image.setFitWidth(300);
        Image imageFoto2 = new Image("anime6.jpg");
        image2.setImage(imageFoto2);
        image2.setFitHeight(500);
        image2.setFitWidth(300);
    }
}
