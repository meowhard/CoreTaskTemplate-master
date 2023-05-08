package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util connectionUtil = new Util();

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Connection connection = connectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGSERIAL NOT NULL, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age SMALLINT not NULL, " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = connectionUtil.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = connectionUtil.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (name, lastName, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = connectionUtil.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE id=" + id + "");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = connectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = connectionUtil.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
