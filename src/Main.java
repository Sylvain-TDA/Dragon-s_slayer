import fr.dragonsslayer.Game;
import fr.dragonsslayer.Menu;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.DefensiveEquipment;
import fr.dragonsslayer.equipment.OffensiveEquipment;

public class Main {
    public static void main(String[] args)  throws InterruptedException {
        Menu menu = new Menu();
        Hero hero = null;
        Game game = new Game();
        boolean exit = false;
        boolean playerCreated = false;
        while (!exit) {
            switch (menu.printMenu()) {
                case 1: // Création personnage
                    hero = menu.createHero();
                    menu.manageHero(hero);
                    playerCreated = true;
                    break;
                case 2: //Jouer
                    if (playerCreated) {
                        game.startingAGame();
                    } else {
                        System.out.println("Vous devez créer un personnage avant de vous lancer dans l'aventure");
                    }
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