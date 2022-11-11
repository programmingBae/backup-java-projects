package DAO;
/**
 * @author AbednegoSteven-1972009
 */
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements daointerface<User>{
    @Override
    public int addData(User data) {
        return 0;
    }

    @Override
    public int delData(User data) {
        return 0;
    }

    @Override
    public int updateData(User data) {
        return 0;
    }

    @Override
    public List<User> showData() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,name,username,password ,position FROM user";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String username =  res.getString("username");
                String password = res.getString("password");
                String position = res.getString("position");
                User user = new User(id,name,username,password,position);
                users.add(user);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return users;
    }
}
