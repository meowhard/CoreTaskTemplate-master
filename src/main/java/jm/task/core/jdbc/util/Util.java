package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String DATABASE_PROPERTIES = "C:\\Projects\\Java\\CoreTaskTemplate-master" +
            "\\src\\main\\java\\jm\\task\\core\\jdbc\\util\\database.properties";

    public Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get(DATABASE_PROPERTIES))){
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/preproject111");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "pass");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        return new Configuration().setProperties(properties).buildSessionFactory();
    }
}
