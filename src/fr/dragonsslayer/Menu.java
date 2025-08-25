package fr.dragonsslayer;
import fr.dragonsslayer.characters.Hero;

import java.util.Scanner;

/**
 * Handle everything related to the menu : introduction, selection, hero's creation...
 */

public class Menu {
    private Scanner keyboard = new Scanner(System.in);

    private String name;
    private String type;


    /**
     * Printing the introduction of the game
     */

    public Menu() {
        String intro = "Bienvenue dans Dragon's Slayer !" + "\n" + "Vous sentez-vous prêt à combattre des ordes d'ennemis et de dragons ?";
        System.out.println(toString(intro));
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName() {
        this.name = requestInput(keyboard, "Avant de vous jetter corps et âmes dans une aventure fantastique, veuillez entrer votre nom");
    }

    public void setType() {
        this.type = requestInput(keyboard, "Quel type de combattant êtes-vous ? Plutôt Warrior ou plutôt Magician ?");
    }

    /**
     * Ask the player what type he wants to play
     * @return the type selected
     */

    public String askType() {
        Scanner scanner = new Scanner(System.in);
        String typeSelection = "Entrez le type (Warrior ou Magician) : ";
        System.out.print(toString(typeSelection));
        return scanner.nextLine();
    }

    /**
     * Ask the player the name of his hero
     * @return the name entered
     */

    public String askName() {
        Scanner scanner = new Scanner(System.in);
        String name = "Entrez le nom : ";
        System.out.print(toString(name));
        return scanner.nextLine();
    }

    /**
     * Display the menu selection
     * @return the selected menu
     */

    public int printMenu() {
        String menu = "                          " + "\n" +
                "===== Menu Principal =====" + "\n" +
                "                          " + "\n" +
                "1. Nouveau personnage" + "\n" +
                "2. Jouer" + "\n" +
                "3. Quitter" + "\n" +
                "Votre choix : ";

        System.out.print(toString(menu));
        return keyboard.nextInt();
    }

    /**
     *  Display the character menu where you can print infos, modify the character created or play
     * @return the selected menu
     */

    public int printCharacterMenu() {
        String characteMenu = "===== Menu Personnage =====" + "\n" +
                "1. Afficher les infos" + "\n" +
        "2. Modifier le personnage" + "\n" +
                "3. Jouer" + "\n" +
                "Votre choix : ";
        System.out.println(toString(characteMenu));
        return keyboard.nextInt();
    }

    /**
     * Creating the hero by choosing the type and the name
     * @return A new hero with a type and a name
     */

    public Hero createHero() {
        return new Hero(askType(), askName());
    }

    /**
     * Est-ce que cette méthode est encore utile ???
     * @param keyboard
     * @param message
     * @return
     */

    public String requestInput(Scanner keyboard, String message) {
        System.out.println(message);
        return keyboard.nextLine();
    }

    /**
     * Handle the character menu and choose between printing the hero's info, modify it of play the game
     * @param hero information modify or not modify
     */

    public void manageHero(Hero hero) {
        boolean play = false;
        Game game = new Game();

        while (!play) {
            switch (printCharacterMenu()) {
                case 1:
                    System.out.println(hero);
                    break;
                case 2:
                    hero.modify(askType(), askName());
                    break;
                case 3:
                    play = true;
                    game.startingAGame();
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }

    public String toString(String message) {
        return message;
    }
}

