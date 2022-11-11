package DAO;
/**
 * @author AbednegoSteven-1972009
 */
import entity.Laboratorium;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LaboratoriumDao implements daointerface<Laboratorium> {
        @Override
        public int addData(Laboratorium data) {
            int result = 0;
            try {
                String query = "INSERT INTO laboratorium (name,capacity) VALUES (?,?)";
                PreparedStatement ps;
                ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setString(1,data.getName());
                ps.setInt(2,data.getCapacity());
                result = ps.executeUpdate();
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            return result;
        }

        @Override
        public int delData(Laboratorium data) {
            int result = 0;
            try {
                String query = "Delete FROM laboratorium WHERE id=?";
                Connection conn = JDBCConnection.getConnection();
                conn.setAutoCommit(false);
                PreparedStatement ps;
                ps= conn.prepareStatement(query);
                ps.setInt(1,data.getId());
                result= ps.executeUpdate();
                if (result != 0){
                    conn.commit();
                }
                else{
                    conn.rollback();
                }

            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return result;
        }

        @Override
        public int updateData(Laboratorium data) {
            int result = 0;
            try {
                String query = "Update laboratorium set name=?, capacity=? where id=?";
                PreparedStatement ps;
                ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setString(1, data.getName());
                ps.setInt(2, data.getCapacity());
                ps.setInt(3, data.getId());
                result = ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return result;
        }

        @Override
        public List<Laboratorium> showData() {
            ObservableList<Laboratorium> cList = FXCollections.observableArrayList();

            try {
                String query = "SELECT * FROM laboratorium;";
                PreparedStatement ps;
                ps = JDBCConnection.getConnection().prepareStatement(query);
                ResultSet res = ps.executeQuery();
                while (res.next()){
                    int id= res.getInt("id");
                    int capacity= res.getInt("capacity");
                    String lab = res.getString("name");
                    Laboratorium cat = new Laboratorium(id, lab, capacity);
                    cList.add(cat);
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
            return cList;
        }
    }

