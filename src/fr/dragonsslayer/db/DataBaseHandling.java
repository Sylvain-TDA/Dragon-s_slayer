package fr.dragonsslayer.db;

import com.google.gson.Gson;
import fr.dragonsslayer.Game;
import fr.dragonsslayer.board.Cell;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.characters.Magician;
import fr.dragonsslayer.characters.Warrior;

import java.sql.*;
import java.util.ArrayList;

/**
 * Handle the relation with the database.
 */

public class DataBaseHandling {

    /**
     * Display heroes in the database.
     */

    public ArrayList<String> getHeroes() {
        String sql = "SELECT `Name` FROM `Character`";
        ArrayList<String> heroes = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                String name = resultSet.getString("Name").trim();
                heroes.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroes;
    }

    public Hero getHeroFromDb(int heroId) {
        String sql = "SELECT * FROM `Character` WHERE Id = ?";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, heroId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int life = resultSet.getInt("lifePoints");
                int attackLevel = resultSet.getInt("Strength");

                return createHeroFromDb(type, name, life, attackLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertHeroAndGetId(Hero hero) {
        String sql = "INSERT INTO `Character` (name, type, lifePoints, Strength) VALUES (?, ?, ?, ?)";
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, hero.getName());
            statement.setString(2, hero.getType());
            statement.setInt(3, hero.getLife());
            statement.setInt(4, hero.getAttackLevel());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Échec de l'insertion du héros, aucune ligne affectée.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Récupère l'ID généré
                } else {
                    throw new SQLException("Échec de la récupération de l'ID généré.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Retourne -1 en cas d'erreur
        }
    }

    private Hero createHeroFromDb(String type, String name, int life, int attackLevel) {
        if ("Warrior".equalsIgnoreCase(type)) {
            Warrior warrior = new Warrior(type, name);
            warrior.setLife(life);
            warrior.setAttackLevel(attackLevel);
            return warrior;
        } else if ("Magician".equalsIgnoreCase(type)) {
            Magician magician = new Magician(type, name);
            magician.setLife(life);
            magician.setAttackLevel(attackLevel);
            return magician;
        } else {
            throw new IllegalArgumentException("Type de héros inconnu : " + type);
        }
    }

    /**
     * Create an hero and store it in the database.
     *
     * @param hero to access the getters of the class Hero.
     */

    private int createHeroes(Hero hero) {
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
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Récupère l'ID généré
                } else {
                    throw new SQLException("Échec de la récupération de l'ID généré.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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

    private void createBoard() {
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

    public void createJsonBoard() {
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

    private void getBoard() {
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
