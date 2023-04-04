package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost/mydb";
    private static final String DB_USERNAME = "ilya";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
        return connection;
    }
}
