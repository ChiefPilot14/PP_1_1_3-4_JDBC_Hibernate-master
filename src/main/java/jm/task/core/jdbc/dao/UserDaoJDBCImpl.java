package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = jm.task.core.jdbc.util.Util.getConnection();
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name varchar(25), lastname varchar(25), age TINYINT, PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().executeUpdate("INSERT INTO users (name, lastname, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "')");
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        Connection connection = Util.getConnection();
        try {
            connection.createStatement().executeUpdate("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        Connection connection = Util.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
