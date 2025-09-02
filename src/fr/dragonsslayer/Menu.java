package fr.dragonsslayer;

import fr.dragonsslayer.board.HeroOutOfTheBoardException;
import fr.dragonsslayer.characters.*;
import fr.dragonsslayer.db.DataBaseHandling;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handle the user interface of Dragon's Slayer, included :
 * - Displaying menu (main, character,...)
 * - Hero's creation and modification
 * - Playing the game
 * Use {@link Scanner} to interact with the player.
 */

public class Menu {
    private final Scanner keyboard;
    private Hero hero;
    private DataBaseHandling db;
    private Game game;

    public Menu() {
        this.db = new DataBaseHandling();
        this.game = new Game();
        this.keyboard = new Scanner(System.in);
    }

    /**
     * Display the intro's logo in ASCII art.
     */

    public void displayIntro() {
        String intro = """
                 /~_______________~\\\s
                 .-----------------.\s
                (| Dragon's slayer |)
                 '-----------------'\s
                 \\_~~~~~~~~~~~~~~~_/\s""";
        game.getBoard();
        displayMessage(intro);
    }

    /**
     * Getter for the name of the hero.
     *
     * @return NullPointerException If no hero has been created.
     */

    public String getName() {
        try {
            return hero.getName();
        } catch (NullPointerException npe) {
            return "Aucun héro créé";
        }
    }

    /**
     * Getter for the type of the hero.
     *
     * @return NullPointerException If no hero has been created.
     */

    public String getType() {
        try {
            return hero.getType();
        } catch (NullPointerException npe) {
            return "Aucun héro créé";
        }
    }

    /**
     * Request the player what type he wants to play, either Warrior or Magician.
     * The Scanner handle what the user entered.
     *
     * @return the type selected under a specific format.
     */

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

    /**
     * Display the menu selection
     *
     * @return the selected menu
     */

    public int displayMenu() {
        int choice = 0;
        boolean valide = false;

        while (!valide) {
            try {
                String menu = """
                                                 \s
                        ===== Menu Principal =====
                                                 \s
                        1. Nouveau personnage
                        2. Jouer
                        3. Quitter
                        """;

                displayMessage(menu);
                choice = keyboard.nextInt();
                keyboard.nextLine();

                if (choice >= 1 && choice <= 4) {
                    valide = true;
                } else {
                    String selectionText = "Veuillez entrer 1, 2 ou 3.";
                    displayMessage(selectionText);
                }
            } catch (InputMismatchException e) {
                displayMessage("Entrée invalide. Veuillez entrer un nombre (1, 2 ou 3).");
                keyboard.nextLine();
            }
        }
        return choice;
    }

    /**
     * Main loop for the menu.
     * Handle the creation of the hero, starting a game or quiting a game.
     *
     * @throws InterruptedException if the thread is interrupted during the game.
     */


    public void mainMenu() throws InterruptedException {
        try {
            boolean exit = false;
            boolean playerCreated = false;

            while (!exit) {
                switch (displayMenu()) {
                    case 1: // Hero creation
                        hero = createHero();
                        manageHero(hero);
                        playerCreated = true;
                        break;
                    case 2: //Playing
                        if (playerCreated) {
                            try {
                                game.startingAGame();
                                game.playingTheGame();
                            } catch (HeroOutOfTheBoardException e) {
                                displayMessage(e.getMessage());
                            }
                        } else {
                            String errorText = "Vous devez créer un personnage avant de vous lancer dans l'aventure";
                            displayMessage(errorText);
                        }
                        break;
                    case 3: // Exit
                        exit = true;
                        String exitText = "Oh non... À toute !";
                        displayMessage(exitText);
                        break;
                    case 4: // For test, create a hero and launch the game

                        try {
                            createCheatedHero();
                            game.startingAGame();
                            game.playingTheGame();
                        } catch (HeroOutOfTheBoardException e) {
                            displayMessage(e.getMessage());
                        }
                        break;
                    default:
                        String invalidChoice = "Choix invalide !";
                        displayMessage(invalidChoice);
                }
            }

        } catch (InterruptedException e) {
            displayMessage("Le jeu a été interrompu.");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Display the character menu where the player must choose between different options.
     *
     * @return the user's choice (1- display hero's information, 2- Modify the hero, 3- Play the game).
     */

    public int displayCharacterMenu() {
        String characterMenu = """
                ===== Menu Personnage =====
                1. Afficher les infos
                2. Modifier le personnage
                3. Pray for respect
                4. Jouer
                Votre choix :\s""";
        displayMessage(characterMenu);
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        return choice;
    }

    /**
     * Creating the hero by choosing the type and the name
     *
     * @return An instance of {@link Warrior} or {@link Magician} corresponding to the user's choice.
     */

    public Hero createHero() {
        Hero hero;

        if (askType().equalsIgnoreCase("Warrior")) {
            hero = new Warrior("Warrior", askName());
        } else {
            hero = new Magician("Magician", askName());
        }
        db.toJson(hero);
        //db.createHeroes(hero);
        return hero;
    }

    /**
     * Create a specific hero for tests (Warrior named "Kanomi").
     *
     * @return An instance of {@link Warrior} with default values.
     */

    public Hero createCheatedHero() {
        return new Warrior("Warrior", "Kanomi");
    }

    /**
     * Handle the hero's menu : display hero's infos, modify the hero or playing the game.
     *
     * @param hero the hero created.
     * @throws InterruptedException if the thread is interrupted during the game.
     */

    public void manageHero(Hero hero) throws InterruptedException {

        boolean play = false;

        while (!play) {
            try {
                switch (displayCharacterMenu()) {
                    case 1:
                        System.out.println(hero);
                        break;
                    case 2:
                        hero.modify(askType(), askName());
                        db.editHeroes(hero);
                        break;
                    case 3:
                        game.prayForRespect();
                        break;
                    case 4:
                        play = true;
                        try {
                            game.initBoard();
                            game.getBoard();
                            game.startingAGame();
                            game.playingTheGame();
                        } catch (HeroOutOfTheBoardException e) {
                            displayMessage(e.getMessage());
                        } catch (Exception e) {
                            displayMessage("Erreur lors du lancement du jeu : " + e.getMessage());
                        }
                        break;
                    default:
                        String invalidChoice = "Choix invalide !";
                        displayMessage(invalidChoice);
                }
            } catch (Exception e) {
                displayMessage("Erreur : " + e.getMessage());
            }
        }
    }

    /**
     * Display a message to the player
     *
     * @param message that will be display
     */

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

