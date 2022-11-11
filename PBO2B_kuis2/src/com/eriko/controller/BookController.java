package com.eriko.controller;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import com.eriko.model.Buku;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    private MainController mainController;

    @FXML
    private Label labelId;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelPublicationYear;
    @FXML
    private TextField textfieldTitle;
    @FXML
    private TextField textfieldPublicationYear;
    @FXML
    private TextField textfieldId;
    @FXML
    private Label labelPublisher;
    @FXML
    private Label labelAuthor;
    @FXML
    private Label labelBookType;
    @FXML
    private Button buttonSave;
    @FXML
    private TextField textfieldPublisher;
    @FXML
    private TextField textfieldAuthor;
    @FXML
    private TextField textfieldBookType;
    @FXML
    private TableView<Buku> tableView;
    @FXML
    private TableColumn<Buku,Integer> col1;
    @FXML
    private TableColumn<Buku,String> col2;
    @FXML
    private TableColumn<Buku,String> col3;
    @FXML
    private TableColumn<Buku,String> col4;
    @FXML
    private TableColumn<Buku,String> col5;
    @FXML
    private TableColumn<Buku,String> col6;

    public void setController(MainController controller) {
        mainController = controller;
        tableView.setItems(mainController.getBooks());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelId.setText(resources.getString("book.id"));
        labelTitle.setText(resources.getString("book.title"));
        labelPublisher.setText(resources.getString("book.publisher"));
        labelPublicationYear.setText(resources.getString("book.publicationYear"));
        labelAuthor.setText(resources.getString("book.author"));
        labelBookType.setText(resources.getString("book.bookType"));
        buttonSave.setText(resources.getString("book.save"));
        col1.setText(resources.getString("book.id"));
        col2.setText(resources.getString("book.title"));
        col3.setText(resources.getString("book.publisher"));
        col4.setText(resources.getString("book.publicationYear"));
        col5.setText(resources.getString("book.author"));
        col6.setText(resources.getString("book.bookType"));

        col1.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        col2.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getJudul()));
        col3.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPenerbit()));
        col4.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTahunTerbit()));
        col5.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPengarang()));
        col6.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getJenisBuku()));
    }

    @FXML
    private void OnPressSave(ActionEvent actionEvent) {
        if(!textfieldId.getText().trim().isEmpty() && !textfieldTitle.getText().trim().isEmpty() && !textfieldPublisher.getText().trim().isEmpty() && !textfieldPublicationYear.getText().trim().isEmpty() && !textfieldAuthor.getText().trim().isEmpty() && !textfieldBookType.getText().trim().isEmpty()){
            try{
                int id = Integer.parseInt(textfieldId.getText());
                Buku book = new Buku();
                book.setId(id);
                book.setJudul(textfieldTitle.getText());
                book.setPenerbit(textfieldPublisher.getText());
                book.setTahunTerbit(textfieldPublicationYear.getText());
                book.setPengarang(textfieldAuthor.getText());
                book.setJenisBuku(textfieldBookType.getText());
                mainController.addBook(book);
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("ID not Numberic");
                alert.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Textfield empty");
            alert.show();
        }
    }
}
