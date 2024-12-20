package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    // реализуйте настройку соеденения с БД
    static final String DB_URL = "jdbc:mysql://localhost:3306/kata_hibernate";
    static final String USER = "katauser";
    static final String PWD = "kata#strong5pwd";
    Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;

    }
}
