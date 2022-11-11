package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.Team;
import sample.entity.User;
import sample.utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamDAO implements daointerface<Team>{

    @Override
    public int addData(Team data) {
        return 0;
    }

    @Override
    public int delData(Team data) {
        return 0;
    }

    @Override
    public int updateData(Team data) {
        return 0;
    }

    @Override
    public List<Team> showData() {
        ObservableList<Team> teams = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM team";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String teamName = res.getString("name");
                Team team = new Team(id,teamName);
                teams.add(team);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return teams;
    }

}
