import fr.dragonsslayer.Menu;
import fr.dragonsslayer.db.DataBaseHandling;
import fr.dragonsslayer.db.DatabaseConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args)  throws InterruptedException {
        DataBaseHandling getHeroes = new DataBaseHandling();
        getHeroes.getHeroes();
        Menu menu = new Menu();
        menu.displayIntro();
        menu.mainMenu();
    }
}