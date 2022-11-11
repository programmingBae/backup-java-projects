package com.example.demo.DAO;

import com.example.demo.Entities.Negara;
import com.example.demo.Entities.Penerbangan;
import com.example.demo.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PenerbanganDao implements daointerface<Penerbangan>{
    @Override
    public int addData(Penerbangan data) {
        int result = 0;
        try {
            String query = "INSERT INTO penerbangan (HargaPenerbangan,Negara_idAsal,Negara_idTujuan,Maskapai) " +
                    "VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setFloat(1,data.getHargaPenerbangan());
            ps.setInt(2,data.getNegara_idAsal().getId());
            ps.setInt(3,data.getNegara_idTujuan().getId());
            ps.setString(4,data.getMaskapai());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(Penerbangan data) {
        return 0;
    }

    @Override
    public int updateData(Penerbangan data) {
        return 0;
    }

    @Override
    public List<Penerbangan> showData() {
        ObservableList<Penerbangan> listPenerbangan = FXCollections.observableArrayList();
        try {
            String query = "SELECT s.idPenerbangan as idPenerbangan, s.HargaPenerbangan, s.Negara_idAsal,s.Negara_idTujuan, d.idNegara, d.Negara as namaNegaraAsal, e.Negara as namaNegaraTujuan, s.Maskapai FROM "+
                    "penerbangan s JOIN negara d ON s.Negara_idAsal = d.idNegara JOIN negara e ON s.Negara_idTujuan = e.idNegara";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("idPenerbangan");
                float harga = res.getFloat("HargaPenerbangan");
                Negara negaraAsal = new Negara();
                int idNegaraAsal = res.getInt("Negara_idAsal");
                String namaNegaraAsal = res.getString("namaNegaraAsal");
                negaraAsal.setId(idNegaraAsal);
                negaraAsal.setNegara(namaNegaraAsal);
                Negara negaraTujuan = new Negara();
                int idNegaraTujuan = res.getInt("Negara_idTujuan");
                String namaNegaraTujuan = res.getString("namaNegaraTujuan");
                negaraTujuan.setId(idNegaraTujuan);
                negaraTujuan.setNegara(namaNegaraTujuan);
                String maskapai = res.getString("Maskapai");

                Penerbangan penerbangan = new Penerbangan(id,harga,negaraAsal,negaraTujuan,maskapai);
                listPenerbangan.add(penerbangan);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return listPenerbangan;
    }

    public List<Penerbangan> showDataByFilter(int idNegara) {
        ObservableList<Penerbangan> listPenerbangan = FXCollections.observableArrayList();
        try {
            String query = "SELECT s.idPenerbangan, s.HargaPenerbangan,d.idNegara as Negara_idAsal, " +
                    "d.Negara as namaNegaraAsal, e.idNegara as Negara_idTujuan, e.Negara as namaNegaraTujuan , s.Maskapai FROM penerbangan s " +
                    "JOIN negara d ON s.Negara_idAsal = d.idNegara JOIN negara e ON s.Negara_idTujuan = e.idNegara " +
                    "WHERE s.Negara_idAsal ="  +idNegara+ " OR s.Negara_idTujuan = "+idNegara;
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("idPenerbangan");
                float harga = res.getFloat("HargaPenerbangan");
                Negara negaraAsal = new Negara();
                int idNegaraAsal = res.getInt("Negara_idAsal");
                String namaNegaraAsal = res.getString("namaNegaraAsal");
                negaraAsal.setId(idNegaraAsal);
                negaraAsal.setNegara(namaNegaraAsal);
                Negara negaraTujuan = new Negara();
                int idNegaraTujuan = res.getInt("Negara_idTujuan");
                String namaNegaraTujuan = res.getString("namaNegaraTujuan");
                negaraTujuan.setId(idNegaraTujuan);
                negaraTujuan.setNegara(namaNegaraTujuan);
                String maskapai = res.getString("Maskapai");

                Penerbangan penerbangan = new Penerbangan(id,harga,negaraAsal,negaraTujuan,maskapai);
                listPenerbangan.add(penerbangan);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return listPenerbangan;
    }
}
