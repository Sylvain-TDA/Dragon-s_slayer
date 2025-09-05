import fr.dragonsslayer.Game.Game;
import fr.dragonsslayer.Hero.HeroManager;
import fr.dragonsslayer.Game.Menu;
import fr.dragonsslayer.db.DataBaseHandling;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DataBaseHandling dbHandling = new DataBaseHandling();

        Menu menu = new Menu(dbHandling, new Game(),new HeroManager(dbHandling));
        menu.displayIntro();
        menu.mainMenu();
    }
}