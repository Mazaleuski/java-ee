package by.teachmeskills.ps.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static final String DB_URL_KEY = "dbUrl";
    private static final String DB_USERNAME_KEY = "dbUsername";
    private static final String DB_PASSWORD_KEY = "dbPassword";

    private DbUtils() {
    }

    public static Connection getConnection() {
        String dbUrl = ConfigUtils.getProperty(DB_URL_KEY);
        String dbUsername = ConfigUtils.getProperty(DB_USERNAME_KEY);
        String dbPassword = ConfigUtils.getProperty(DB_PASSWORD_KEY);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}