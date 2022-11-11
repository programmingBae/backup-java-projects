package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.DAO.TeamDAO;
import sample.DAO.UserDAO;
import sample.entity.Team;
import sample.entity.User;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField txtNRP;
    @FXML
    private TextField txtNama;
    @FXML
    private ComboBox<Team> comboTeam;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, String> nrp;
    @FXML
    private TableColumn<User, String> nama;
    @FXML
    private TableColumn<User, String> team;

    private ObservableList<User> users;
    private ObservableList<Team> teams;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UserDAO userDAO = new UserDAO();
        TeamDAO teamDAO = new TeamDAO();
        teams = (ObservableList<Team>) teamDAO.showData();
        comboTeam.setItems(teams);
        users =userDAO.showData();
            tableUser.setItems(users);
            nrp.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
            nama.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getNama()));
            team.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getTeam().getName()));



    }

    public void addData(ActionEvent actionEvent) {
        User user =new User();
        user.setId(Integer.valueOf(txtNRP.getText()));
        user.setNama(txtNama.getText());
        user.setTeam(comboTeam.getValue());
        UserDAO userDAO = new UserDAO();
        userDAO.addData(user);
        users.clear();
        users.addAll(userDAO.showData());
        tableUser.refresh();


    }

    public void refresh(ActionEvent actionEvent) {
        tableUser.refresh();
    }

    public void deleteData(ActionEvent actionEvent) {
        User selected;
        selected = (User) tableUser.getSelectionModel().getSelectedItem();
        UserDAO userDAO = new UserDAO();
        int result = userDAO.delData(selected);
        if (result==0){
            System.out.println("Delete Gagal");
        } else {
            System.out.println("Delete Berhasil");
        }
        users.clear();
        users.addAll(userDAO.showData());
    }

    public void updateData(ActionEvent actionEvent) {
        txtNRP.setDisable(true);
        User selected;
        selected = (User) tableUser.getSelectionModel().getSelectedItem();
        selected.setNama(txtNama.getText());
        selected.setTeam(comboTeam.getValue());
        UserDAO userDAO = new UserDAO();
        int result = userDAO.updateData(selected);
        if (result==0){
            System.out.println("Update Gagal");
        } else {
            System.out.println("Update Berhasil");
            txtNRP.setDisable(false);
        }
        users.clear();
        users.addAll(userDAO.showData());
    }

    public void selectedItem(MouseEvent mouseEvent) {
        User selected;
        selected = (User) tableUser.getSelectionModel().getSelectedItem();
        txtNama.setText(selected.getNama());
        txtNRP.setText(String.valueOf(selected.getId()));
        comboTeam.getSelectionModel().select(selected.getTeam());

    }
}
