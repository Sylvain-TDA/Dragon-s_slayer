package fr.dragonsslayer.db;

import java.sql.*;

public class DataBaseHandling {

    public void getHeroes() {
        String sql = "SELECT `Name` FROM `Character`";
        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name").trim();
                System.out.println("Hero Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
