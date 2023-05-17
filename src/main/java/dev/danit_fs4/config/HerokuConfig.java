package dev.danit_fs4.config;

import dev.danit_fs4.db.DataBase;

public class HerokuConfig implements Config {
    public enum Env {
        JDBC_DATABASE_URL("JDBC_DATABASE_URL"),
        PORT("PORT"),
        JDBC_DATABASE_USERNAME("JDBC_DATABASE_USERNAME"),
        JDBC_DATABASE_PASSWORD("JDBC_DATABASE_PASSWORD"),

        LOCAL_MACHINE_DATABASE_URL(DataBase.URL),
        LOCAL_MACHINE_DATABASE_USERNAME(DataBase.USERNAME),

        LOCAL_MACHINE_DATABASE_PASSWORD(DataBase.PASSWORD);
        private final String env;
        Env(String env) {
            this.env = env;
        }

        public String value() {
            return env;
        }
    }
    public static final boolean isDev = System
            .getenv(Env.JDBC_DATABASE_URL.value()) == null
            && System.getenv(Env.PORT.value()) == null
            && System.getenv(Env.JDBC_DATABASE_USERNAME.value()) == null
            && System.getenv(Env.JDBC_DATABASE_PASSWORD.value()) == null;

    public String url() {
        return !isDev ? System.getenv(Env.JDBC_DATABASE_URL.value()) : Env.LOCAL_MACHINE_DATABASE_URL.value();
    }

    public String username() {
        return !isDev ? System.getenv(Env.JDBC_DATABASE_USERNAME.value()) : Env.LOCAL_MACHINE_DATABASE_USERNAME.value();
    }

    public String password() {
        return !isDev ? System.getenv(Env.JDBC_DATABASE_PASSWORD.value()) : Env.LOCAL_MACHINE_DATABASE_PASSWORD.value();
    }

    public int port(int defaultPort) {
        try {
            String port = System.getenv(Env.PORT.value());
            return !isDev ? Integer.parseInt(port) : defaultPort;
        } catch (NumberFormatException ex) {
            return defaultPort;
        }
    }

}
