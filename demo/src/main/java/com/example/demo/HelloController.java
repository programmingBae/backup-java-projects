package com.example.demo;

import com.example.demo.DAO.NegaraDao;
import com.example.demo.DAO.PenerbanganDao;
import com.example.demo.Entities.Negara;
import com.example.demo.Entities.Penerbangan;
import com.example.demo.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    public ComboBox comboFilter;
    public TableView tablePenerbangan;
    public TableColumn colAsal;
    public TableColumn colId;
    public TableColumn colTujuan;
    public TableColumn colHarga;
    public TableColumn colMaskapai;
    private ObservableList<Penerbangan> listPenerbangan;
    private ObservableList listNegara;
    private PenerbanganDao penerbanganDao = new PenerbanganDao();
    private NegaraDao negaraDao = new NegaraDao();


    public void initialize(){
        listPenerbangan = (ObservableList<Penerbangan>) penerbanganDao.showData();
        listNegara = FXCollections.observableArrayList();
        listNegara.add(0, "Tanpa Filter");
        listNegara.addAll(negaraDao.showData());
        comboFilter.setItems(listNegara);
        comboFilter.getSelectionModel().select(0);
        tablePenerbangan.setItems(listPenerbangan);
        colId.setCellValueFactory(new PropertyValueFactory<>("idPenerbangan"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("HargaPenerbangan"));
        colAsal.setCellValueFactory(new PropertyValueFactory<>("Negara_idAsal"));
        colTujuan.setCellValueFactory(new PropertyValueFactory<>("Negara_idTujuan"));
        colMaskapai.setCellValueFactory(new PropertyValueFactory<>("Maskapai"));

    }

    public void addData(ActionEvent actionEvent) {
    }

    public void deleteData(ActionEvent actionEvent) {
    }

    public void handleClicks(ActionEvent actionEvent) {
            listPenerbangan.clear();
            try {
                Negara negara = (Negara) comboFilter.getSelectionModel().getSelectedItem();
                listPenerbangan.addAll(penerbanganDao.showDataByFilter(negara.getId()));
            } catch (ClassCastException ex){
                listPenerbangan.addAll(penerbanganDao.showData());
            }
    }

    public void openReport(ActionEvent actionEvent) {
        JasperPrint jasperPrint;
        Map param = new HashMap();
        if (Negara.class.isInstance(comboFilter.getSelectionModel().getSelectedItem())){
            Negara negara = (Negara) comboFilter.getSelectionModel().getSelectedItem();
            param.put("idPenerbanganAsal", negara.getId());
            param.put("idPenerbanganTujuan", negara.getId());
        } else {
            param.put("idPenerbanganAsal", null);
            param.put("idPenerbanganTujuan", null);
        }


        try {
            String sourcePath = "Report/GroupBy.jasper";
            String destinationPath = "Report/File1.jrxml";

            JasperReport report = (JasperReport) JRLoader.loadObject(new File(sourcePath));
            JRXmlWriter.writeReport(report, destinationPath, "UTF-8");
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }
}