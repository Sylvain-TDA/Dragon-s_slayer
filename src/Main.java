import fr.dragonsslayer.Game;
import fr.dragonsslayer.Menu;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.DefensiveEquipment;
import fr.dragonsslayer.equipment.OffensiveEquipment;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Hero hero = null;
        Game game = new Game();
        boolean exit = false;
        while (!exit) {
            switch (menu.printMenu()) {
                case 1: // Création personnage
                    hero = menu.createHero();
                    menu.manageHero(hero);
                    break;
                    case 2: //Jouer
                        game.startingAGame();
                        break;
                case 3: // Quitter
                    exit = true;
                    System.out.println("Oh non... À toute !");
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}