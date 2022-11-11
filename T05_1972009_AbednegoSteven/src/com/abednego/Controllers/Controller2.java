package com.abednego.Controllers;
/**
 * @author AbednegoSteven-1972009
 */

import com.abednego.DAO.CategoryDAO;
import com.abednego.DAO.ItemsDAO;
import com.abednego.entity.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    public Text textAja;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<Category> tableCategory;
    @FXML
    private TableColumn<Category,String> kolomID;
    @FXML
    private TableColumn<Category, String> kolomName;

    private ObservableList<Category> categories;
    private boolean categoryDuplikat;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
        textAja.setText(this.controller.getItems1().getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CategoryDAO categoryDAO = new CategoryDAO();
        categories = (ObservableList<Category>) categoryDAO.showData();
        tableCategory.setItems(categories);
        kolomID.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        kolomName.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getName()));

    }

    public void addData(ActionEvent actionEvent) {
        if (txtName.getText().trim().isEmpty() || txtID.getText().trim().isEmpty()){
            Alert alertInformation=new Alert(Alert.AlertType.INFORMATION);
            alertInformation.setContentText("Please fill in all the field");
            alertInformation.show();
        } else {
            try {
                for (int i = 0; i < categories.size();i++){
                    if (categories.get(i).getId()==Integer.parseInt(txtID.getText())){
                        categoryDuplikat = true;
                        break;
                    } else {
                        categoryDuplikat = false;
                    }
                }
                Category category = new Category();
                category.setId(Integer.valueOf(txtID.getText()));
                category.setName(txtName.getText());


                if (categoryDuplikat != true){
                    CategoryDAO categoryDAO = new CategoryDAO();
                    categoryDAO.addData(category);
                    controller.getCategories().clear();
                    controller.getCategories().addAll(categoryDAO.showData());
                    categories.clear();
                    categories.addAll(categoryDAO.showData());
                } else {
                    Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                    alertInformation.setContentText("Supplier dengan ID yang sama sudah ada di dalam table");
                    alertInformation.show();
                }
                txtName.setText("");
                txtID.setText("");
            } catch (NumberFormatException ex) {
                Alert alertInformation=new Alert(Alert.AlertType.ERROR);
                alertInformation.setContentText("Please only input number for ID");
                alertInformation.show();
            }

        }
    }
}
