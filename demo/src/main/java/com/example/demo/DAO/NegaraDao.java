package com.example.demo.DAO;

import com.example.demo.Entities.Negara;
import com.example.demo.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NegaraDao implements daointerface<Negara>{
    @Override
    public int addData(Negara data) {
        return 0;
    }

    @Override
    public int delData(Negara data) {
        return 0;
    }

    @Override
    public int updateData(Negara data) {
        return 0;
    }

    @Override
    public List<Negara> showData() {
        ObservableList<Negara> listNegara = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM negara";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("idNegara");
                String negaraNama = res.getString("Negara");
                Negara negara = new Negara(id,negaraNama);
                listNegara.add(negara);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return listNegara;
    }
}
