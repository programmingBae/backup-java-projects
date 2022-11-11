package com.abednego.DAO;
/**
 * @author AbednegoSteven-1972009
 */
import com.abednego.entity.Category;
import com.abednego.entity.Items;
import com.abednego.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemsDAO implements daointerface<Items>{
    @Override
    public int addData(Items data) {
        int result = 0;
        try {
            String query = "INSERT INTO items (name,price,description,category_id) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getName());
            ps.setDouble(2,data.getPrice());
            ps.setString(3,data.getDescription());
            ps.setInt(4,data.getCategory().getId());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(Items data) {
        int result = 0;
        try {
            Connection connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false);
            String query = "DELETE from items WHERE id=? ";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            result = ps.executeUpdate();
            if (result!=0){
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public int delManyData(ObservableList<Items> selectedItems) {
        int result = 0;
        try {
            for (int i = 0; i < selectedItems.size();i++) {
                Connection connection = JDBCConnection.getConnection();
                connection.setAutoCommit(false);
                String query = "DELETE from items WHERE id=? ";
                PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setInt(1, selectedItems.get(i).getId());
                result = ps.executeUpdate();
                if (result != 0) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Items data) {
        int result = 0;
        try {
            Connection connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false);
            String query = "UPDATE items SET name=?, price=?, category_id=? WHERE id=? ";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getName());
            ps.setDouble(2,data.getPrice());
            ps.setInt(3,data.getCategory().getId());
            ps.setInt(4,data.getId());
            result = ps.executeUpdate();
            if (result!=0){
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<Items> showData() {
        ObservableList<Items> items = FXCollections.observableArrayList();
        try {
            String query = "SELECT s.id as itemID, s.name, s.price, d.id, d.name as nama FROM "+
                    "items s JOIN category d ON s.category_id = d.id";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("nama");
                Category category = new Category(id,name);
                Items item = new Items();
                item.setId(res.getInt("itemID"));
                item.setName(res.getString("name"));
                item.setPrice(res.getDouble("price"));
                item.setCategory(category);
                items.add(item);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }


        return items;
    }

}
