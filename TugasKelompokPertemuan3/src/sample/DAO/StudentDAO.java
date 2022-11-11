package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Student;
import sample.utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO implements daointerface<Student> {
    @Override
    public int addData(Student data) {
        int result = 0;
        try {
            String query = "INSERT INTO student (nama,kat,uts,uas,rata) VALUES (?,?,?,?,?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getNama());
            ps.setDouble(2,data.getKat());
            ps.setDouble(3,data.getUts());
            ps.setDouble(4,data.getUas());
            ps.setDouble(5,data.getNilaiRataRata());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(Student data) {
        return 0;
    }

    @Override
    public int updateData(Student data) {
        return 0;
    }

    @Override
    public ObservableList<Student> showData() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM student;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                String nama = res.getString("nama");
                double kat = res.getDouble("kat");
                double uts = res.getDouble("uts");
                double uas = res.getDouble("uas");
                Student student = new Student(nama, kat,uts,uas);
                students.add(student);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }


        return students;
    }
}
