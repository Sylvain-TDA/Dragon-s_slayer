import fr.dragonsslayer.Menu;
import fr.dragonsslayer.db.DataBaseConnector;

public class Main {
    public static void main(String[] args)  throws InterruptedException {
        DataBaseConnector connector = new DataBaseConnector();
        connector.queryCharacters();
        Menu menu = new Menu();
        menu.displayIntro();
        menu.mainMenu();
    }
}