package Module1;

/**
 * @author Abednego Steven - 1972009
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Controller {
    public Label paperText;
    public Label scissorsText;
    public Label rockText;
    private int paper;
    private int rock;
    private int scissor;
    private int loseCount;
    private int drawCount;
    private int winCount;
    private int temp = 4;
    private int computerChoice;
    @FXML
    private Button scissorsRock;
    @FXML
    private Button paperRock;
    @FXML
    private Button rockButton;
    @FXML
    private ImageView label1;
    @FXML
    private ImageView label2;
    @FXML
    private Label lose;
    @FXML
    private Label draw;
    @FXML
    private Label win;

    public void updateComputer(){
        Random random = new Random();
        computerChoice = random.nextInt(3);
        if (computerChoice==0){ // paper
            Image imageUser = new Image(getClass().getResource("1.jpg").toExternalForm());
            label1.setOpacity(1);
            label1.setDisable(false);
            label1.setImage(imageUser);

        } else if (computerChoice==1){ // rock
            Image imageUser = new Image(getClass().getResource("2.jpg").toExternalForm());
            label1.setOpacity(1);
            label1.setDisable(false);
            label1.setImage(imageUser);

        } else{ // scissors
            Image imageUser = new Image(getClass().getResource("3.jpg").toExternalForm());
            label1.setOpacity(1);
            label1.setDisable(false);
            label1.setImage(imageUser);

        }

        if (temp==0){
            if (computerChoice==0){
                loseCount+=1;
            } else if (computerChoice==1){
                drawCount+=1;
            } else if (computerChoice==2){
                winCount+=1;
            }
        } else if (temp==1){
            if (computerChoice==0){
                winCount+=1;
            } else if (computerChoice==1){
                loseCount+=1;
            } else if (computerChoice==2) {
                drawCount+=1;
            }
        } else {
            if (computerChoice==0){
                drawCount+=1;
            } else if (computerChoice==1){
                winCount+=1;
            } else if (computerChoice==2) {
                loseCount+=1;
            }
        }

        win.setText(String.valueOf(winCount));
        lose.setText(String.valueOf(loseCount));
        draw.setText(String.valueOf(drawCount));
    }

    public void Rock(ActionEvent actionEvent) {
        Image imageUser = new Image(getClass().getResource("2.jpg").toExternalForm());
        label2.setOpacity(1);
        label2.setImage(imageUser);


        if (temp!=0){
            rock += 1;
        }else{
            rock += 0;
        }

        temp = 0;
        rockText.setText(String.valueOf(rock));
        updateComputer();

    }


    public void Scissors(ActionEvent actionEvent) {
        Image imageUser = new Image(getClass().getResource("3.jpg").toExternalForm());
        label2.setOpacity(1);
        label2.setImage(imageUser);
        if (temp!=1){
            scissor += 1;
        }else{
            scissor += 0;
        }

        temp = 1;
        scissorsText.setText(String.valueOf(scissor));
        updateComputer();
    }

    public void Paper(ActionEvent actionEvent) {
        Image imageUser = new Image(getClass().getResource("1.jpg").toExternalForm());
        label2.setOpacity(1);
        label2.setImage(imageUser);
        if (temp!=2){
            paper += 1;
        }else{
            paper += 0;
        }

        temp = 2;
        paperText.setText(String.valueOf(paper));
        updateComputer();
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }
}
