package com.bulka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с базой данных установлено!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка соединения с базой данных: " + e.getMessage());
        }
    }
}
