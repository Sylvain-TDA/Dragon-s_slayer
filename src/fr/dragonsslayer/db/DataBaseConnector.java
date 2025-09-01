package fr.dragonsslayer.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DataBaseConnector {
        public  void queryCharacters(){
            String url = "jdbc:mysql://localhost:3306/dragons_slayer";
            String user = "laravel";
            String password = "laravel";
            try (
               Connection connection = DriverManager.getConnection(url, user, password);

               Statement statement = connection.createStatement();

               ResultSet resultSet = statement.executeQuery("SELECT * FROM `Character`");
            ) {
                while(resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String name = resultSet.getString("Name").trim();
                    System.out.println("ID : " + id + ", Name : " + name);
                }
            }catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

