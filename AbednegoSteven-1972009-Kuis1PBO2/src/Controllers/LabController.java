package Controllers;

import DAO.LaboratoriumDao;
import entity.Laboratorium;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author AbednegoSteven-1972009
 */
public class LabController implements Initializable {
    public TableView<Laboratorium> tableLab;
    public TableColumn<Laboratorium, String> id;
    public TableColumn<Laboratorium, String > name;
    public TableColumn<Laboratorium, String> capacity;
    private MainController controller;
    private ObservableList<Laboratorium> laboratoriums;

    public void setController(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LaboratoriumDao laboratoriumDao = new LaboratoriumDao();
        laboratoriums = (ObservableList<Laboratorium>) laboratoriumDao.showData();
        tableLab.setItems(laboratoriums);
        id.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        name.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getName()));
        capacity.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getCapacity())));
    }
}
