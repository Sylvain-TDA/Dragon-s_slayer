package fr.dragonsslayer.db;

import com.google.gson.Gson;
import fr.dragonsslayer.Game;
import fr.dragonsslayer.board.Cell;
import fr.dragonsslayer.characters.Hero;

import java.sql.*;
import java.util.ArrayList;

/**
 * Handle the relation with the database.
 */

public class DataBaseHandling {

    /**
     * Display heroes in the database.
     */

    public void getHeroes() {
        String sql = "SELECT `Name` FROM `Character`";
        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name").trim();
                System.out.println("Nom du héro : " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create an hero and store it in the database.
     *
     * @param hero to access the getters of the class Hero.
     */

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
            //System.out.println(rowsInserted + " ligne(s) insérée(s). CREATEHERO");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modify the hero and store the changes in the database.
     *
     * @param hero to access the getters of the class Hero.
     */

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
            //System.out.println(rowsInserted + " ligne(s) modifiée(s). EDITHERO");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modify the life points and sotre it in the database.
     *
     * @param hero to access the getters of the class Hero.
     */

    public void changeLifePoints(Hero hero) {
        String sql = "UPDATE `Character` " +
                "SET `LifePoints` = ? " +
                "WHERE `Id` = (SELECT MAX(`Id`) FROM `Character`)";
        // ----------------
        // le code sera à modifier ici suivant les nouveaux points de vie que l'on veut intégrer.

        int newLifePoints = 1;
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, newLifePoints);

            int rowsInserted = statement.executeUpdate();
            //System.out.println(rowsInserted + " ligne(s) modifiée(s). CHANGELIFEPOINTS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the board and store it in the database.
     */

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
            //System.out.println(rowsInserted.length + " ligne(s) insérée(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the board in Json and store it in the database.
     */

    public void createJsonBoard () {
        Game game = new Game();
        Gson gson = new Gson();
        game.initBoard();

        String json = gson.toJson(game.getBoard());
        String sql = "INSERT INTO `Board` (`Type`) " +
                "VALUES (?)";
        try (
                Connection connection = DatabaseConnection.getConnection();

                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, json);

            int rowsInserted = statement.executeUpdate();
            //System.out.println(rowsInserted + " ligne(s) insérée(s).TOJSON");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Empty the table Board as it should only store the cells of the game played.
     */

    public void deleteBoard() {
        String sql = "DELETE FROM `Board` WHERE Id > 0";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            int rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the board from the database and display it.
     */

    public void getBoard() {
        String sql = "SELECT * FROM `Board`";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                String type = resultSet.getString("Type").trim();
                int id = resultSet.getInt("Id");
                System.out.println("Board position : " + id + " Type : " + type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Convert the object hero into a Json and store it in the database.
     *
     * @param hero to access the getters of the class Hero.
     */

    public void toJson(Hero hero) {
        Gson gson = new Gson();
        String json = gson.toJson(hero);
        String sql = "INSERT INTO `Character` (`Type`, `Name`, `LifePoints`, `Strength`, `OffensiveEquipment`, `DefensiveEquipment`, `JsonHero`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            statement.setString(7, json);

            int rowsInserted = statement.executeUpdate();
            //System.out.println(rowsInserted + " ligne(s) insérée(s).TOJSON");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
