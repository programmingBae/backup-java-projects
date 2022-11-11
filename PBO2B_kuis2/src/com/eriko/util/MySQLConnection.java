package com.eriko.util;
/**
 * Eriko Agustino
 * 1972041
 * 6 January 2022
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String url = "jdbc:mysql://localhost:3306/kuis2pbo2";
    private static final String username = "root";
    private static final String password = "";

    public static Connection CreateConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,username,password);
        connection.setAutoCommit(false);
        return connection;
    }
}
