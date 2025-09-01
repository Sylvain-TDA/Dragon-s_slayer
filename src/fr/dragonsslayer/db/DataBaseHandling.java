package fr.dragonsslayer.db;

import fr.dragonsslayer.characters.Hero;

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

    public void createHeroes(Hero hero) {
        String sql = "INSERT INTO `Character` (`Type`, `Name`, `LifePoints`, `Strength`, `OffensiveEquipment`, `DefensiveEquipment`) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, hero.getType());
            statement.setString(2, hero.getName());
            statement.setInt(3, hero.getLife());
            statement.setInt(4, hero.getAttackLevel());
            statement.setString(5, hero.getOffensiveEquipment());
            statement.setString(6, hero.getDefensiveEquipment());

            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " ligne(s) insérée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
