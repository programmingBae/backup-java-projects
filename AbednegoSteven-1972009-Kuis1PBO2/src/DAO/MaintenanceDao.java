package DAO;
/**
 * @author AbednegoSteven-1972009
 */
import entity.Laboratorium;
import entity.Maintenance;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JDBCConnection;

import java.sql.*;
import java.util.List;


public class MaintenanceDao implements daointerface<Maintenance>{
    @Override
    public int addData(Maintenance data) {
        int result = 0;
        try {
            String query ="INSERT INTO maintenance (id,user_id,laboratorium_id,task, date) VALUES (?,?,?,?,?) ";
            PreparedStatement ps;
            ps =JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setInt(2, data.getUser().getId());
            ps.setInt(3,data.getLaboratorium().getId());
            ps.setString(4, data.getTask());
            ps.setDate(5, (Date) data.getDate());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delData(Maintenance data) {
        int result = 0;
        try {
            String query = "DELETE FROM maintenance WHERE id = ?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, data.getId());
            result = ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Maintenance data) {
        int result = 0;
        try {
            String query = "UPDATE maintenance SET user_id = ?, laboratorium_id = ?, task = ?, date = ? WHERE id = ?";
            Connection conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setInt(1, data.getUser().getId());
            ps.setInt(2, data.getLaboratorium().getId());
            ps.setString(3, data.getTask());
            ps.setString(4, String.valueOf(data.getDate()));
            ps.setInt(5, data.getId());
            result = ps.executeUpdate();
            if (result != 0){
                conn.commit();
            }else {
                conn.rollback();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Maintenance> showData() {
        ObservableList<Maintenance> maintenances = FXCollections.observableArrayList();
        try {
            String query = "SELECT s.id as mainID, s.user_id as userId, d.name as nama, e.name as labNama, e.capacity, s.laboratorium_id as labId, task, date FROM "+
                    "maintenance s JOIN user d ON s.user_id = d.id JOIN laboratorium e ON s.laboratorium_id = e.id";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int labId = res.getInt("labId");
                String labName = res.getString("labNama");
                int capacity = res.getInt("capacity");
                Laboratorium lab = new Laboratorium(labId,labName,capacity);
                int userId = res.getInt("userId");
                String userName = res.getString("nama");
                User user = new User();
                user.setId(userId);
                user.setName(userName);
                Maintenance maintenance = new Maintenance();
                maintenance.setId(res.getInt("mainId"));
                maintenance.setLaboratorium(lab);
                maintenance.setUser(user);
                maintenance.setDate(res.getDate("date"));
                maintenance.setTask(res.getString("task"));

                maintenances.add(maintenance);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }


        return maintenances;
    }
}
