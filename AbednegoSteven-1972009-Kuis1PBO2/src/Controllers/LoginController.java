package Controllers;
/**
 * @author AbednegoSteven-1972009
 */
import DAO.UserDao;
import entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private ObservableList<User> users;
    private boolean login;
    @FXML
    private GridPane gridPane;
    private int indexUser;

    public void login(ActionEvent actionEvent) throws IOException {
        UserDao userDao = new UserDao();
        if (username.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            Alert alertError=new Alert(Alert.AlertType.ERROR);
            alertError.setContentText("Please fill in all the field");
            alertError.show();
        } else {
            users = (ObservableList<User>) userDao.showData();
            for (int i = 0; i<users.size();i++){
                if (users.get(i).getUsername().equals(username.getText()) &&
                        users.get(i).getPassword().equals(password.getText())) {
                    login = true;
                    openNewStage();
                    break;
                } else {
                    login = false;
                }

            }
            if (login==false){
                Alert alertError=new Alert(Alert.AlertType.ERROR);
                alertError.setContentText("Password / Username salah");
                alertError.show();
                login = true;
            }
        }
    }

    public void openNewStage() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/main-view.fxml"));
        Parent root = loader.load();
        MainController controller2 =loader.getController();
        controller2.setController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(gridPane.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Kuis 1 - 1972009");
        newStage.show();
    }

    public int getIndexUser() {
        return indexUser;
    }

    public ObservableList<User> getUsers() {
        return users;
    }
}
