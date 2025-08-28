import fr.dragonsslayer.Game;
import fr.dragonsslayer.HeroOutOfTheBoardException;
import fr.dragonsslayer.Menu;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.DefensiveEquipment;
import fr.dragonsslayer.equipment.OffensiveEquipment;

public class Main {
    public static void main(String[] args)  throws InterruptedException {
        Menu menu = new Menu();
        Hero hero;
        Game game = new Game();
        menu.printIntro();

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
                        try {
                            game.startingAGame();
                            game.playingTheGame();
                        } catch (HeroOutOfTheBoardException e) {
                            menu.toString(e.getMessage());
                        }
                    } else {
                        String errorText = "Vous devez créer un personnage avant de vous lancer dans l'aventure";
                        menu.toString(errorText);
                    }
                    break;
                case 3: // Quitter
                    exit = true;
                    String exitText = "Oh non... À toute !";
                    menu.toString(exitText);
                    break;
                default:
                    String invalidChoise = "Choix invalide !";
                    menu.toString(invalidChoise);
            }
        }
    }
}