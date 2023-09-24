package by.teachmeskills.shop.repositories;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
public class ConnectionPool{
    private static volatile ConnectionPool instance;

    private static final String PROPERTY_FILE = "application";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASS = "db.pass";
    private static final int MAX_CON_COUNT = 100;
    private static final int MIN_CON_COUNT = 5;

    private static final String url;
    private static final String login;
    private static final String password;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY_FILE);
        url = resourceBundle.getString(DB_URL);
        login = resourceBundle.getString(DB_LOGIN);
        password = resourceBundle.getString(DB_PASS);
    }

    private volatile int currentConCount = MIN_CON_COUNT;
    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(MAX_CON_COUNT, true);

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private ConnectionPool() {
        for (int i = 0; i < MIN_CON_COUNT; i++) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                pool.add(DriverManager.getConnection(url, login, password));
            } catch (SQLException e) {
                log.warn(e.getMessage());
            } catch (ClassNotFoundException e) {
                log.warn(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private void openAdditionalConnection() throws Exception {
        try {
            pool.add(DriverManager.getConnection(url, login, password));
            currentConCount++;
        } catch (SQLException e) {
            log.warn("Connection wasn't add!");
            throw new Exception("Connection wasn't add!", e);
        }
    }

    public Connection getConnection() throws Exception {
        Connection connection;
        try {
            if (pool.isEmpty() && currentConCount < MAX_CON_COUNT) {
                openAdditionalConnection();
            }
            connection = pool.take();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.warn("Max count of connections!");
            throw new Exception("Max count of connections!");
        }
        return connection;
    }

    public void closeConnection(Connection connection) throws Exception {
        if (connection != null) {
            if (currentConCount > MIN_CON_COUNT) {
                currentConCount--;
            }
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("Connection wasn't returned!");
                throw new Exception("Connection wasn't returned!");
            }
        }
    }
}
