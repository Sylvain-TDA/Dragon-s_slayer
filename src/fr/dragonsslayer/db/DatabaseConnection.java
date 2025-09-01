package fr.dragonsslayer.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/dragons_slayer";
    private static final String USER = "laravel";
    private static final String PASSWORD = "laravel";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

