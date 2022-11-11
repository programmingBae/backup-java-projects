import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl {
    public int addData(Course course) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "INSERT INTO course(id,course.name,credits,lab_session,semester,curriculum_id) "+"VALUES(?,?,?,?,?,?)";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1, course.getId());
                ps.setString(2,course.getName());
                ps.setInt(3,course.getCredits());
                ps.setBoolean(4,course.isLabSession());
                ps.setString(5,course.getSemester());
                ps.setInt(6,course.getCurriculum().getId());
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
    public int updateData(Course course) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "UPDATE course SET course.name = ?, credits = ?, lab_session  = ?, semester = ?, "
                +"curriculum_id = ? WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1,course.getName());
                ps.setInt(2,course.getCredits());
                ps.setBoolean(3,course.isLabSession());
                ps.setString(4,course.getSemester());
                ps.setInt(5,course.getCurriculum().getId());
                ps.setString(6,course.getId());
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
    public int deleteData(Course course) throws SQLException, ClassNotFoundException {
        int result = 0;
        String query = "DELETE FROM course WHERE id = ?";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1,course.getId());
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
    public List<Course> fetchAll() throws SQLException, ClassNotFoundException {
        List<Course> courseList = new ArrayList<>();
        String query = "SELECT s.id, s.name as nama, credits, lab_session, semester, curriculum_id,d.name as namas FROM "+
                "course s JOIN curriculum d ON s.curriculum_id = d.id";
        try (Connection connection = MySQLConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while(resultSet.next()){
                        Curriculum curriculum = new Curriculum();
                        curriculum.setId(resultSet.getInt("curriculum_id"));
                        curriculum.setName(resultSet.getString("namas"));
                        Course course = new Course();
                        course.setId(resultSet.getString("id"));
                        course.setName(resultSet.getString("nama"));
                        course.setCredits(resultSet.getInt("credits"));
                        course.setLabSession(resultSet.getBoolean("lab_session"));
                        course.setSemester(resultSet.getString("semester"));
                        course.setCurriculum(curriculum);
                        courseList.add(course);
                    }
                }
            }
        }


        return courseList;
    }
}
