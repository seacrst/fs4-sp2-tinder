package dev.danit_fs4.db;

import dev.danit_fs4.config.Config;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

public class DataBase {
    private static Connection connection;
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "pg123456";
    public static void checkAndApplyDeltas(Config serviceConfig) {
        FluentConfiguration conf = new FluentConfiguration()
                .dataSource(serviceConfig.url(), serviceConfig.username(), serviceConfig.password());
        Flyway flyway = new Flyway(conf);
        flyway.migrate();
    }
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

    public static Optional<Connection> connect(Config config) {
        return connect(config.url(), config.username(), config.password());
    }

    public static void setConnection(Connection con) {
        connection = con;
    }

    public static Connection getConnection() {
        return connection;
    }

}
