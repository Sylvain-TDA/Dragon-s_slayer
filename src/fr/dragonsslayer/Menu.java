package fr.dragonsslayer;

import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.characters.Warrior;
import fr.dragonsslayer.characters.Magician;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handle everything related to the menu : introduction, selection, hero's creation...
 */

public class Menu {
    private final Scanner keyboard = new Scanner(System.in);

    private String name;
    private String type;


    /**
     * Printing the introduction of the game
     */

    public Menu() {
    }

    public void printIntro (){
        String intro = " /~_______________~\\ \n" +
                " .-----------------. \n" +
                "(| Dragon's slayer |)\n" +
                " '-----------------' \n" +
                " \\_~~~~~~~~~~~~~~~_/ ";
        toString(intro);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    /**
     * Ask the player what type he wants to play
     *
     * @return the type selected
     */

    public String askType() {
        String typeSelection;
        String userInput;

        while (true) {
            typeSelection = "Entrer le type (Warrior ou Magician) : ";
            toString(typeSelection);
            userInput = keyboard.nextLine().trim();

            if (userInput.equalsIgnoreCase("Warrior") || userInput.equalsIgnoreCase("Magician")) {
                return userInput.substring(0, 1).toUpperCase() + userInput.substring(1).toLowerCase();
            } else {
                String errorText = "Vous devez entrer soit 'Warrior' soit 'Magician'";
                toString(errorText);
            }
        }
    }

    /**
     * Ask the player the name of his hero
     *
     * @return the name entered
     */

    public String askName() {
        String name = "Entrez le nom : ";
        toString(name);
        return keyboard.nextLine();
    }

    /**
     * Display the menu selection
     *
     * @return the selected menu
     */

    public int printMenu() {
        int choice = 0;
        boolean valide = false;

        while (!valide) {
            try {
                String menu = "                          " + "\n" +
                        "===== Menu Principal =====" + "\n" +
                        "                          " + "\n" +
                        "1. Nouveau personnage" + "\n" +
                        "2. Jouer" + "\n" +
                        "3. Quitter" + "\n" +
                        "Votre choix : ";

                toString(menu);
                choice = keyboard.nextInt();
                keyboard.nextLine();

                if (choice >= 1 && choice <= 3) {
                    valide = true;
                } else {
                    String selectionText = "Veuillez entrer 1, 2 ou 3.";
                    toString(selectionText);
                }
            } catch (InputMismatchException e) {
                String errorText = "Entrée invalide. Veuillez entrer un nombre (1, 2 ou 3).";
                toString(errorText);
                keyboard.next();
            }
        }
        return choice;
    }

    /**
     * Display the character menu where you can print infos, modify the character created or play
     *
     * @return the selected menu
     */

    public int printCharacterMenu() {
        String characterMenu = "===== Menu Personnage =====" + "\n" +
                "1. Afficher les infos" + "\n" +
                "2. Modifier le personnage" + "\n" +
                "3. Jouer" + "\n" +
                "Votre choix : ";
        toString(characterMenu);
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        return choice;
    }

    /**
     * Creating the hero by choosing the type and the name
     *
     * @return A new hero with a type and a name
     */

    public Hero createHero() {

        if (askType().equalsIgnoreCase("Warrior")) {
            return new Warrior("Warrior", askName());
        } else {
            return new Magician("Magician", askName());
        }
    }

    /**
     * Handle the character menu and choose between printing the hero's info, modify it of play the game
     *
     * @param hero information modify or not modify
     */

    public void manageHero(Hero hero) throws InterruptedException {
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
                    String invalidChoice = "Choix invalide !";
                    toString(invalidChoice);
            }
        }
    }

    /**
     * This method handle everything that need to be printed out.
     *
     * @param message that will be display
     */

    public void toString(String message) {
        System.out.println(message);
    }
}

