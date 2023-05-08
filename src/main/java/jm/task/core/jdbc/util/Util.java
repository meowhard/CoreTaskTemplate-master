package jm.task.core.jdbc.util;

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
}
