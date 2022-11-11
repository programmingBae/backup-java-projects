package Kuis2.controllers;

/**
 * @author AbednegoSteven-1972009
 */
import Kuis2.dao.BukuDaoImpl;
import Kuis2.entities.BukuEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class BookManagementController implements Initializable {
    @FXML
    public TableView<BukuEntity> tableView;
    @FXML
    public TableColumn<BukuEntity,String> col1;
    @FXML
    public TableColumn<BukuEntity,String> col2;
    @FXML
    public TableColumn<BukuEntity,String> col3;
    @FXML
    public TableColumn<BukuEntity,String> col4;
    @FXML
    public TableColumn<BukuEntity,String> col5;
    @FXML
    public TableColumn<BukuEntity,String> col6;
    @FXML
    public TextField txtTitle;
    @FXML
    public TextField txtPublication;
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtPublisher;
    @FXML
    public TextField txtAuhtor;
    @FXML
    public TextField txtBookType;

    private ObservableList<BukuEntity> bukuEntities = FXCollections.observableArrayList();
    private BukuDaoImpl bukuDao = new BukuDaoImpl();
    private MainViewController mainViewController;

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bukuEntities = (ObservableList<BukuEntity>) bukuDao.getAll();
        tableView.setItems(bukuEntities);
        col1.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        col2.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getJudul()));
        col3.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPenerbit()));
        col4.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getTahunTerbit()));
        col5.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getPengarang()));
        col6.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getJenisBuku()));
    }

    public void saveData(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty() || txtAuhtor.getText().isEmpty() || txtTitle.getText().isEmpty()
        || txtBookType.getText().isEmpty() || txtPublication.getText().isEmpty() ||
                txtPublisher.getText().isEmpty()){
            Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                BukuEntity bukuEntity = new BukuEntity();
                bukuEntity.setId(Integer.parseInt(txtId.getText().trim()));
                bukuEntity.setJudul(txtTitle.getText().trim());
                bukuEntity.setPenerbit(txtPublisher.getText().trim());
                bukuEntity.setPengarang(txtAuhtor.getText().trim());
                bukuEntity.setJenisBuku(txtBookType.getText().trim());
                bukuEntity.setTahunTerbit(txtPublication.getText().trim());
                bukuDao.addData(bukuEntity);
                bukuEntities.clear();
                bukuEntities.addAll(bukuDao.getAll());
                txtId.setText("");
                txtTitle.setText("");
                txtPublisher.setText("");
                txtAuhtor.setText("");
                txtBookType.setText("");
                txtPublication.setText("");
            } catch (NumberFormatException ex){
                Alert alertInformation = new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }
        }
    }
}
