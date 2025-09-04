package fr.dragonsslayer.Hero;

import fr.dragonsslayer.db.DataBaseHandling;

import java.util.Scanner;

public class HeroManager {
    private final Scanner keyboard;
    private final DataBaseHandling db;

    public HeroManager(DataBaseHandling db) {
        this.keyboard = new Scanner(System.in);
        this.db = db;
    }

    public Hero createHero() {
        Hero hero;
        if (askType().equalsIgnoreCase("Warrior")) {
            hero = new Warrior("Warrior", askName());
        } else {
            hero = new Magician("Magician", askName());
        }
        db.toJson(hero);
        return hero;
    }

    public Hero createCheatedHero() {
        Hero hero;
        hero = new Warrior("Warrior", "Desctructor");
        db.toJson(hero);
        return hero;
    }

    public String askType() {
        String typeSelection;
        String userInput;
        int trynbr = 0;

        while (true) {
            displayMessage("""
                    
                    Vous allez devoir nous dire de quoi vous êtes fait. Plutôt :\s
                     - Warrior
                    ou plutôt
                     - Magician\s
                    """);
            userInput = keyboard.nextLine().trim();

            if (userInput.isEmpty()) {
                displayMessage("\n" + "Vous devez entrer un type. Réessayez.");
                trynbr++;
                continue;
            }

            if (userInput.equalsIgnoreCase("Warrior") || userInput.equalsIgnoreCase("Magician")) {
                return userInput.substring(0, 1).toUpperCase() + userInput.substring(1).toLowerCase();
            } else {
                displayMessage("\n" + "Vous devez entrer soit 'Warrior' soit 'Magician'");
                trynbr++;
            }

            if (trynbr < 4) {
                displayMessage("");
            } else if (trynbr > 4 && trynbr < 9) {
                displayMessage("""
                        \s
                        On va y arriver... courage.
                        """);
            } else if (trynbr > 10) {
                displayMessage("""
                        \s
                         Je retire ce que j'ai dit. C'est peine perdu.
                        \s
                          ___________.._______
                         | .__________))______|
                         | | / /      ||
                         | |/ /       ||
                         | | /        ||.-''.
                         | |/         |/  _  \\
                         | |          ||  `/,|
                         | |          (\\\\`_.'
                         | |         .-`--'.
                         | |        /Y . . Y\\
                         | |       // |   | \\\\
                         | |      //  | . |  \\\\
                         | |     ')   |   |   (`
                         | |          ||'||
                         | |          || ||
                         | |          || ||
                         | |          || ||
                         | |         / | | \\
                         ""\"""\"""\""|_`-' `-' |""\"|
                         |"|""\"""\""\\ \\       '"|"|
                         | |        \\ \\        | |
                         : :         \\ \\       : :  
                         . .          `'       . .
                        \s""");
            }
        }
    }

    /**
     * Request the player to enter the name of his hero.
     * The Scanner handle what the user entered.
     *
     * @return the name entered.
     */

    public String askName() {
        String name;
        while (true) {
            displayMessage("Et votre nom est ? ");
            name = keyboard.nextLine().trim();
            if (name.isEmpty()) {
                displayMessage("Bien tenté ' '");
            } else if (name.length() > 10) {
                displayMessage("Un chasseur de dragon doit malheureusement raccourcir son nom.");
            } else if (!name.matches("[a-zA-Z0-9 ]+")) { // Autorise lettres, chiffres et espaces
                displayMessage("Le nom contient des caractères interdits. Réessayez.");
            } else {
                return name;
            }
        }
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
