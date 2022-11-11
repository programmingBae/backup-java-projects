package com.abednego.DAO;
/**
 * @author AbednegoSteven-1972009
 */
import com.abednego.entity.Category;
import com.abednego.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO implements daointerface<Category>{
    @Override
    public int addData(Category data) {
        int result = 0;
        try {
            String query = "INSERT INTO category (id,name) VALUES (?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getName());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(Category data) {
        return 0;
    }

    @Override
    public int updateData(Category data) {
        return 0;
    }

    @Override
    public List<Category> showData() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM category";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String categoryName = res.getString("name");
                Category category = new Category(id,categoryName);
                categories.add(category);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return categories;
    }
}
