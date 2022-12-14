import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/schoop1";
    private static final String USERNAME = "root"; //harus add user baru di phpMyAdmin atau bisa pake "root"
    private static final String PASSWORD = "";

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        // Class.forName("com.mysql.cj.jdbc.Driver"); // MySql ver 8 ->
        Class.forName("com.mysql.jdbc.Driver"); // MySQL ver 5
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        connection.setAutoCommit(false);
        return connection;

    }
}
