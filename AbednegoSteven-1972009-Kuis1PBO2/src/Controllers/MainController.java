package Controllers;
/**
 * @author AbednegoSteven-1972009
 */
import DAO.LaboratoriumDao;
import DAO.MaintenanceDao;
import entity.Laboratorium;
import entity.Maintenance;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.applet.Main;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button deleteButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button addButton;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtUser;
    @FXML
    public DatePicker txtDate;
    @FXML
    public TextArea txtTask;
    @FXML
    public BorderPane borderPane;
    @FXML
    private MenuItem userManagemen;
    @FXML
    private TableView<Maintenance> tableMaintenance;
    @FXML
    private TableColumn<Maintenance,String> kolomId;
    @FXML
    private TableColumn<Maintenance,String> kolomUser;
    @FXML
    private TableColumn<Maintenance,String> kolomLab;
    @FXML
    private TableColumn<Maintenance,String> kolomTask;
    @FXML
    private TableColumn<Maintenance, String> kolomDate;
    private ObservableList<Maintenance> maintenances;
    private ObservableList<Laboratorium> laboratoriums;
    @FXML
    private ComboBox comboLab;


    private LoginController controller;
    public void setController(LoginController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MaintenanceDao maintenanceDao = new MaintenanceDao();
        LaboratoriumDao laboratoriumDao = new LaboratoriumDao();
        maintenances = (ObservableList<Maintenance>) maintenanceDao.showData();
        laboratoriums = (ObservableList<Laboratorium>) laboratoriumDao.showData();
        comboLab.setItems(laboratoriums);
        tableMaintenance.setItems(maintenances);
        kolomId.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        kolomUser.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser().getName()));
        kolomLab.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getLaboratorium().getName()));
        kolomTask.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getTask()));
        kolomDate.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getDate())));


    }

    public void updateData(ActionEvent actionEvent) {
        if (txtTask.getText().isEmpty()){
            Alert alertError=new Alert(Alert.AlertType.ERROR);
            alertError.setContentText("Please fill in all the field");
            alertError.show();
        } else {
            Maintenance maintenance;
            maintenance = (Maintenance) tableMaintenance.getSelectionModel().getSelectedItem();
            maintenance.setId(Integer.valueOf(txtId.getText()));
            maintenance.setTask(txtTask.getText());
        }
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);
    }

    public void deleteData(ActionEvent actionEvent) {
        Maintenance maintenance;
        maintenance = (Maintenance) tableMaintenance.getSelectionModel().getSelectedItem();
        MaintenanceDao maintenanceDao = new MaintenanceDao();
        int result = maintenanceDao.delData(maintenance);
        maintenances.clear();
        maintenances.addAll(maintenanceDao.showData());
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);
    }

    public void selectedItem(MouseEvent mouseEvent) {
        if (!tableMaintenance.getSelectionModel().getSelectedCells().isEmpty()){
            updateButton.setDisable(false);
            deleteButton.setDisable(false);
            addButton.setDisable(true);
        }
        Maintenance maintenance;
        maintenance = (Maintenance) tableMaintenance.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(maintenance.getId()));
        txtUser.setText(maintenance.getUser().getName());
        comboLab.getSelectionModel().select(maintenance.getLaboratorium());
        txtTask.setText(maintenance.getTask());
    }

    public void openUserManagement(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/user-management-view.fxml"));
        Parent root = loader.load();
        //MainController controller2 =loader.getController();
        //controller2.setController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(borderPane.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Kuis 1 - 1972009");
        newStage.show();
    }

    public void openLabManagement(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/lab-management-view.fxml"));
        Parent root = loader.load();
        LabController controller2 =loader.getController();
        controller2.setController(this);
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.initOwner(borderPane.getScene().getWindow());
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.setTitle("Kuis 1 - 1972009");
        newStage.show();
    }
}
