package dev.danit_fs4.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

public class DataBase {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pg123456";
    public static Optional<Connection> connect(String url, String username, String password) {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", username);
            properties.setProperty("password", password);
            properties.setProperty("autoReconnect", "true");

            return Optional.of(DriverManager.getConnection(url, properties));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<Connection> connect() {
        return connect(URL, USERNAME, PASSWORD);
    }

}
