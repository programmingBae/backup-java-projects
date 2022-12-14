package sample.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection (){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/oop_db",
                    "root",
                    ""
            );
        }
        catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}
