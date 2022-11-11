/**
 * @author AbednegoSteven - 1972009
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Controller {
    @FXML
    private TextArea text;
    private Path p;


    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(text.getScene().getWindow());
        p = Paths.get(f.toURI());
        try {
            text.clear();
            List<String> allLines = Files.readAllLines(p);
            for (String s: allLines){
                text.appendText(s+'\n');
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }


    }

    public void saveAsFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showSaveDialog(text.getScene().getWindow());
        p = Paths.get(f.toURI());
        try{
            Files.write(p,text.getText().getBytes());
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void saveFile(ActionEvent actionEvent) {
        if (p!=null){
            try{
                Files.write(p,text.getText().getBytes());
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            FileChooser fileChooser = new FileChooser();
            File f = fileChooser.showSaveDialog(text.getScene().getWindow());
            p = Paths.get(f.toURI());
            try{
                Files.write(p,text.getText().getBytes());
            } catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
