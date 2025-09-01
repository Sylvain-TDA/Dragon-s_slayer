package fr.dragonsslayer.db;

import fr.dragonsslayer.Game;
import fr.dragonsslayer.board.Cell;
import fr.dragonsslayer.characters.Hero;

import java.sql.*;
import java.util.ArrayList;

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

    public void editHeroes(Hero hero) {
        String sql = "UPDATE `Character` " +
                "SET `Type` = ?, `Name` = ?, `LifePoints` = ?, `Strength` = ?, `OffensiveEquipment` = ?, `DefensiveEquipment` = ? " +
                "WHERE Id = (SELECT MAX(Id) FROM `Character`)";
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
            System.out.println(rowsInserted + " ligne(s) modifiée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeLifePoints(Hero hero) {
        String sql = "UPDATE `Character` " +
                "SET `LifePoints` = ?" +
                "WHERE Id = (SELECT MAX(Id) FROM `Character`)";
        // ----------------
        // le code sera à modifier ici suivant les nouveaux points de vie que l'on veut intégrer.

        int newLifePoints = 1;
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(3, newLifePoints);

            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " ligne(s) modifiée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBoard() {
        String sql = "INSERT INTO `Board` (`Type`) VALUES (?)";
        Game game = new Game();
        game.initBoard();
        ArrayList<Cell> cells = game.getBoard();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            for (Cell cell : cells) {
                statement.setString(1, cell.getType());
                statement.addBatch();
            }
            int[] rowsInserted = statement.executeBatch();
            System.out.println(rowsInserted.length + " ligne(s) insérée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getBoard() {
        String sql = "SELECT * FROM `Board`";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                String type = resultSet.getString("Type").trim();
                int  id = resultSet.getInt("Id");
                System.out.println("Board position : " + id + " Type : " + type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
