package com.example.teori3.DAO;

import com.example.teori3.Model.User;
import com.example.teori3.Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements daoInterface<User>{
    @Override
    public int addData(User data) {
        int result = 0;
        try{
                String query = "INSERT INTO user (name) values (?)";
                PreparedStatement ps;
                ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setString(1, data.getName());
                result = ps.executeUpdate();
        } catch(SQLException ex){

        }
        return result;
    }

    @Override
    public int updateData(User data) {
        return 0;
    }

    @Override
    public int deleteData(User data) {
        return 0;
    }

    @Override
    public List<User> showData() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM user";
        PreparedStatement ps;
        try {
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String nama = res.getString("name");

                User user = new User(id,nama);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
