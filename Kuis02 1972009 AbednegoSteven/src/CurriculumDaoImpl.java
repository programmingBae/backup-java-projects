import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDaoImpl {
    public int addData(Curriculum curriculum) throws SQLException,
            ClassNotFoundException {
        int result = 0;
        String query = "INSERT INTO curriculum(curriculum.name) VALUES(?)";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, curriculum.getName());
                if(ps.executeUpdate()!= 0){
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
    public int updateData(Curriculum curriculum) throws SQLException,
            ClassNotFoundException {
        int result = 0;
        String query = "UPDATE curriculum SET curriculum.name = ? WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, curriculum.getName());
                ps.setInt(2,curriculum.getId());
                if(ps.executeUpdate()!= 0){
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
    public int deleteData(Curriculum curriculum) throws SQLException,
            ClassNotFoundException {
        int result = 0;
        String query = "DELETE FROM curriculum WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setInt(2,curriculum.getId());
                if(ps.executeUpdate()!= 0){
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
    public List<Curriculum> fetchAll() throws SQLException,
            ClassNotFoundException {
        List<Curriculum> curriculumList = new ArrayList<>();
        String query = "SELECT id, name  FROM curriculum";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while(resultSet.next()){
                        Curriculum curriculum = new Curriculum();
                        curriculum.setId(resultSet.getInt("id"));
                        curriculum.setName(resultSet.getString("name"));
                        curriculumList.add(curriculum);
                    }
                }
            }
        }
        return curriculumList;
    }
}
