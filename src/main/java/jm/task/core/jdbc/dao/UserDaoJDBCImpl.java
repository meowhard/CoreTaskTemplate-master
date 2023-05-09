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
            String SQL = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGSERIAL NOT NULL, " +
                    " name VARCHAR(50), " +
                    " lastName VARCHAR (50), " +
                    " age SMALLINT not NULL, " +
                    " PRIMARY KEY (id))";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = connectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users");
            preparedStatement.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = connectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = connectionUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users");
            preparedStatement.executeUpdate();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
