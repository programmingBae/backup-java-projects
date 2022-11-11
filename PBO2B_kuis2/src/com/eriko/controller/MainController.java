package com.eriko.controller;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */
import com.eriko.Main;
import com.eriko.dao.BookDao;
import com.eriko.dao.MemberDao;
import com.eriko.dao.PeminjamanDao;
import com.eriko.model.Anggota;
import com.eriko.model.Buku;
import com.eriko.model.Peminjaman;
import com.eriko.model.ResourceLanguage;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import sun.security.util.Pem;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController implements Initializable {
    private ObservableList<Anggota> members;
    private ObservableList<Buku> books;
    private ObservableList<Peminjaman> rentals;
    private ObservableList<ResourceLanguage> resourceBundles;

    private BookDao bookDao;
    private MemberDao memberDao;
    private PeminjamanDao peminjamanDao;

    @FXML
    private Label labelId;
    @FXML
    private Label labelBorrowingDate;
    @FXML
    private Label labelMember;
    @FXML
    private Label labelDateReturn;
    @FXML
    private Label labelBook;
    @FXML
    private Label labelLanguage;
    @FXML
    private ComboBox<Anggota> comboMember;
    @FXML
    private TextField textfieldId;
    @FXML
    private ComboBox<ResourceLanguage> comboLanguage;
    @FXML
    private ComboBox<Buku> comboBook;
    @FXML
    private DatePicker datepickerBorrowingDate;
    @FXML
    private DatePicker datepickerDateReturn;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private TableView<Peminjaman> tableView;
    @FXML
    private TableColumn<Peminjaman,Integer> col1;
    @FXML
    private TableColumn<Peminjaman,Anggota> col2;
    @FXML
    private TableColumn<Peminjaman,Buku> col3;
    @FXML
    private TableColumn<Peminjaman, Date> col4;
    @FXML
    private TableColumn<Peminjaman, Date> col5;
    @FXML
    private BorderPane MainView;

    @FXML
    private void OnSelectLanguage(ActionEvent actionEvent) {
        UpdateLanguage(comboLanguage.getValue().getResource());
    }

    @FXML
    private void onPressedSave(ActionEvent actionEvent) {
        if(!textfieldId.getText().trim().isEmpty() && comboMember.getValue() != null && comboBook.getValue() != null && datepickerBorrowingDate.getValue() != null && datepickerDateReturn.getValue() != null){
            try{
                int id = Integer.parseInt(textfieldId.getText());
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setId(id);
                peminjaman.setAnggota(comboMember.getValue());
                peminjaman.setBuku(comboBook.getValue());
                peminjaman.setTanggalPinjam(Date.valueOf(datepickerBorrowingDate.getValue()));
                peminjaman.setTanggalPengembalian(Date.valueOf(datepickerDateReturn.getValue()));

                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        Boolean addPeminjaman = peminjamanDao.AddData(peminjaman);
                        return addPeminjaman;
                    }
                };
                task.setOnSucceeded( workerStateEvent -> {
                        FetchAllData();
                    }
                );

                ExecutorService service = Executors.newCachedThreadPool();
                service.execute(task);
                service.shutdown();

            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Id Not Numberic");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Textfield Empty");
        }
    }

    @FXML
    private void OnPressedUpdate(ActionEvent actionEvent) {
        if(comboMember.getValue() != null && comboBook.getValue() != null && datepickerBorrowingDate.getValue() != null && datepickerDateReturn.getValue() != null){
            try{
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setId(Integer.parseInt(textfieldId.getText()));
                peminjaman.setAnggota(comboMember.getValue());
                peminjaman.setBuku(comboBook.getValue());
                peminjaman.setTanggalPinjam(Date.valueOf(datepickerBorrowingDate.getValue()));
                peminjaman.setTanggalPengembalian(Date.valueOf(datepickerDateReturn.getValue()));

                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        Boolean updatePeminjaman = peminjamanDao.UpdateData(peminjaman);
                        return updatePeminjaman;
                    }
                };
                task.setOnSucceeded( workerStateEvent -> {
                        FetchAllData();
                        Reset();
                    }
                );

                ExecutorService service = Executors.newCachedThreadPool();
                service.execute(task);
                service.shutdown();

            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Id Not Numberic");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Textfield Empty");
        }
    }

    @FXML
    private void OnPressedDelete(ActionEvent actionEvent) {
        if (!textfieldId.getText().trim().isEmpty()) {
            try {
                int id = Integer.parseInt(textfieldId.getText());
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setId(id);
                peminjaman.setBuku(comboBook.getValue());
                peminjaman.setAnggota(comboMember.getValue());
                peminjaman.setTanggalPengembalian(Date.valueOf(datepickerDateReturn.getValue()));
                peminjaman.setTanggalPinjam(Date.valueOf(datepickerBorrowingDate.getValue()));

                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        Boolean deletePeminjaman = peminjamanDao.DeleteData(peminjaman);
                        return deletePeminjaman;
                    }
                };
                task.setOnSucceeded( workerStateEvent -> {
                            FetchAllData();
                            Reset();
                        }
                );

                ExecutorService service = Executors.newCachedThreadPool();
                service.execute(task);
                service.shutdown();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Id Not Numberic");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Textfield Empty");
        }
    }

    @FXML
    private void OnPressedReset(ActionEvent actionEvent) {
        Reset();
    }

    public void Reset(){
        textfieldId.clear();
        textfieldId.setEditable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        datepickerDateReturn.setValue(null);
        datepickerBorrowingDate.setValue(null);
        comboBook.setValue(null);
        comboMember.setValue(null);
    }

    @FXML
    private void OnSelectTable(MouseEvent mouseEvent) {
        int selected = tableView.getSelectionModel().getSelectedIndex();
        if(selected > -1){
            ObservableList<Peminjaman> peminjamanList = getPeminjaman();
            if(!peminjamanList.isEmpty()){
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnSave.setDisable(true);

                textfieldId.setText(String.valueOf(peminjamanList.get(selected).getId()));
                datepickerBorrowingDate.setValue(peminjamanList.get(selected).getTanggalPinjam().toLocalDate());
                datepickerDateReturn.setValue(peminjamanList.get(selected).getTanggalPengembalian().toLocalDate());
                comboMember.setValue(peminjamanList.get(selected).getAnggota());
                comboBook.setValue(peminjamanList.get(selected).getBuku());

                textfieldId.setEditable(false);
            }
        }
    }


    @FXML
    private void OnPressShowMemberManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ui/MemberManagement.fxml"));
        if(comboLanguage.getValue() == null){
            loader.setResources(ResourceBundle.getBundle("bundle"));
        }else{
            loader.setResources(comboLanguage.getValue().getResource());
        }

        Parent root = loader.load();
        MemberController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Member Management");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(MainView.getScene().getWindow());

        stage.show();
    }

    @FXML
    private void OnPressShowBookManagement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("ui/bookManagement.fxml"));
        if(comboLanguage.getValue() == null){
            loader.setResources(ResourceBundle.getBundle("bundle"));
        }else{
            loader.setResources(comboLanguage.getValue().getResource());
        }

        Parent root = loader.load();
        BookController controller = loader.getController();
        controller.setController(this);
        Stage stage = new Stage();
        stage.setTitle("Book Management");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(MainView.getScene().getWindow());
        stage.show();
    }

    public void addMember(Anggota anggota){
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean addMember = memberDao.AddData(anggota);
                return addMember;
            }
        };
        task.setOnSucceeded( workerStateEvent -> {
                FetchAllData();
            }
        );

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

    public void addBook(Buku book){
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean addBook = bookDao.AddData(book);
                return addBook;
            }
        };
        task.setOnSucceeded( workerStateEvent -> {
                FetchAllData();
            }
        );

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }

    public ObservableList<Anggota> getMembers(){
        return members;
    }

    public ObservableList<Buku> getBooks(){
        return books;
    }

    public ObservableList<Peminjaman> getPeminjaman(){
        return rentals;
    }

    @FXML
    private void OnPressExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    private void FetchAllData(){
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(asyncBook());
        service.execute(asyncMember());
        service.execute(asyncPeminjaman());
        service.shutdown();
    }

    private Task<List<Buku>> asyncBook() {
        Task<List<Buku>> task = new Task<List<Buku>>() {
            @Override
            protected List<Buku> call() throws Exception {
                return bookDao.FetchAllData();
            }
        };
        task.setOnSucceeded(workerStateEvent ->{
            books.clear();
            books.addAll(task.getValue());
        });
        return task;
    }

    private Task<List<Anggota>> asyncMember() {
        Task<List<Anggota>> task = new Task<List<Anggota>>() {
            @Override
            protected List<Anggota> call() throws Exception {
                return memberDao.FetchAllData();
            }
        };
        task.setOnSucceeded(workerStateEvent ->{
            members.clear();
            members.addAll(task.getValue());
        });
        return task;
    }

    private Task<List<Peminjaman>> asyncPeminjaman() {
        Task<List<Peminjaman>> task = new Task<List<Peminjaman>>() {
            @Override
            protected List<Peminjaman> call() throws Exception {
                return peminjamanDao.FetchAllData();
            }
        };
        task.setOnSucceeded(workerStateEvent ->{
            rentals.clear();
            rentals.addAll(task.getValue());
        });
        return task;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateLanguage(resources);
        books = FXCollections.observableArrayList();
        members = FXCollections.observableArrayList();
        rentals = FXCollections.observableArrayList();
        resourceBundles = FXCollections.observableArrayList();

        bookDao = new BookDao();
        memberDao = new MemberDao();
        peminjamanDao = new PeminjamanDao();

        resourceBundles.add(new ResourceLanguage("bundle_en"));
        resourceBundles.add(new ResourceLanguage("bundle_id"));

        FetchAllData();

        col1.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());
        col2.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getAnggota()));
        col3.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getBuku()));
        col4.setCellValueFactory(d -> new SimpleObjectProperty<Date>(d.getValue().getTanggalPinjam()));
        col5.setCellValueFactory(d -> new SimpleObjectProperty<Date>(d.getValue().getTanggalPengembalian()));

        Reset();
        tableView.setItems(rentals);
        comboMember.setItems(members);
        comboBook.setItems(books);
        comboLanguage.setItems(resourceBundles);

    }

    private void UpdateLanguage(ResourceBundle resources){
        labelLanguage.setText(resources.getString("main.language"));
        labelId.setText(resources.getString("main.id"));
        labelBorrowingDate.setText(resources.getString("main.borrowingDate"));
        labelDateReturn.setText(resources.getString("main.dateReturn"));
        labelMember.setText(resources.getString("main.member"));
        labelBook.setText(resources.getString("main.book"));
        btnSave.setText(resources.getString("main.save"));
        btnUpdate.setText(resources.getString("main.update"));
        btnDelete.setText(resources.getString("main.delete"));
        btnReset.setText(resources.getString("main.reset"));
        col1.setText(resources.getString("main.id"));
        col2.setText(resources.getString("main.member"));
        col3.setText(resources.getString("main.book"));
        col4.setText(resources.getString("main.borrowingDate"));
        col5.setText(resources.getString("main.dateReturn"));
    }
}
