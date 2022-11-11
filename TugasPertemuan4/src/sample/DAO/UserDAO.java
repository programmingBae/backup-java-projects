package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.Team;
import sample.entity.User;
import sample.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements daointerface<User> {
    @Override
    public int addData(User data) {
        int result = 0;
        try {
            Connection connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false);
            String query = "INSERT INTO user (id,name,team_id) VALUES (?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getNama());
            ps.setInt(3,data.getTeam().getId());
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
    public int delData(User data) {
        int result = 0;
        try {
            Connection connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false);
            String query = "DELETE from user WHERE id=? ";
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

    @Override
    public int updateData(User data) {
        int result = 0;
        try {
            Connection connection = JDBCConnection.getConnection();
            connection.setAutoCommit(false);
            String query = "UPDATE user SET name=?, team_id=? WHERE id=? ";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setInt(2,data.getTeam().getId());
            ps.setInt(3,data.getId());
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
    public ObservableList<User> showData() {
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            String query = "SELECT s.id, s.name, team_id, d.name as nama FROM "+
                    "user s JOIN team d ON s.team_id = d.id";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int teamId = res.getInt("team_id");
                String teamName = res.getString("nama");
                Team team = new Team(teamId,teamName);
                User user = new User();
                user.setId(res.getInt("id"));
                user.setNama(res.getString("name"));
                user.setTeam(team);
                users.add(user);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }


        return users;
    }
}
