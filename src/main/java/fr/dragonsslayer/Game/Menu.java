package fr.dragonsslayer.Game;

import fr.dragonsslayer.db.DataBaseHandling;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import fr.dragonsslayer.Hero.*;
import fr.dragonsslayer.equipment.Weapon;

/**
 * Handles the user interface of Dragon's Slayer, including:
 * - Displaying menus (main, character, fight)
 * - Hero's creation and modification
 * - Starting and playing the game
 */
public class Menu {
    private final Scanner keyboard;
    private final DataBaseHandling db;
    private final Game game;
    private final HeroManager heroManager;

    public Menu(DataBaseHandling db, Game game, HeroManager heroManager) {
        this.keyboard = new Scanner(System.in);
        this.db = db;
        this.game = game;
        this.heroManager = heroManager;
    }

    /**
     * Creates and loads a hero using HeroManager.
     */
    private Hero createAndLoadHero() {
        Hero newHero = heroManager.createHero();

        return newHero;
    }

    /**
     * Displays the intro logo in ASCII art.
     */
    public void displayIntro() {
        String intro = """
                 /~_______________~\\
                 .-----------------.
                (| Dragon's slayer |)
                 '-----------------'
                 \\_~~~~~~~~~~~~~~~_/
                """;
        displayMessage(intro);
    }

    /**
     * Displays the main menu and returns the user's choice.
     */
    protected int displayMenu() {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            try {
                String menu = """
                        ===== Menu Principal =====
                        1. Nouveau personnage
                        2. Quitter
                        """;
                displayMessage(menu);
                choice = keyboard.nextInt();
                keyboard.nextLine();
                if (choice >= 1 && choice <= 3) {
                    valid = true;
                } else {
                    displayMessage("Veuillez entrer 1 ou 2.");
                }
            } catch (InputMismatchException e) {
                displayMessage("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
                keyboard.nextLine();
            }
        }
        return choice;
    }

    /**
     * Handle the display for the difficulty selection
     *
     * @return an int choose by the player that indicate the difficulty
     */
    protected int displayDifficulty() {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            try {
                String menu = """
                        ===== Difficulté =====
                            1. Balade
                            2. Comme prévu
                            3. Aïe
                        """;
                displayMessage(menu);
                choice = keyboard.nextInt();
                keyboard.nextLine();
                if (choice >= 1 && choice <= 4) {
                    valid = true;
                } else {
                    displayMessage("Veuillez entrer 1, 2 ou 3.");
                }
            } catch (InputMismatchException e) {
                displayMessage("Entrée invalide. Veuillez entrer un nombre (1, 2 ou 3).");
                keyboard.nextLine();
            }
        }
        return choice;
    }

    /**
     * Handle the difficulty selection by initializing the board depending on the choice.
     */
    public void difficultyMenu() {
        InventoryHandler inventoryHandler = new InventoryHandler();
        switch (displayDifficulty()) {
            case 1: // Balade
                game.initBoard(1);
                break;
            case 2: // Comme prévu
                game.initBoard(2);
                break;
            case 3: // Aïe
                game.initBoard(3);
                break;
            case 4: // Pour les tests, pipe le board
                game.initBoard(4);
                break;
            default:
                displayMessage("Choix invalide !");

        }
    }

    /**
     * Main loop for the menu.
     * Handles hero creation, starting the game, or quitting.
     */
    public void mainMenu() throws InterruptedException {
        boolean exit = false;
        while (!exit) {
            switch (displayMenu()) {
                case 1: // Hero creation
                    Hero hero = createAndLoadHero();
                    game.setHero(hero);
                    manageHero(hero);
                case 2: // Exit
                    exit = true;
                    displayMessage("Oh non... À toute !");
                    break;
                case 3:
                    Hero cheatedHero = heroManager.createCheatedHero();
                    game.setHero(cheatedHero);
                    difficultyMenu();
                    game.start();
            }
        }
    }

    /**
     * Displays the character menu and returns the user's choice.
     */
    private int displayCharacterMenu() {
        String characterMenu = """
                ===== Menu Personnage =====
                1. Afficher les infos
                2. Modifier le personnage
                3. Pray for respect
                4. Jouer
                Votre choix :
                """;
        displayMessage(characterMenu);
        int choice = keyboard.nextInt();
        keyboard.nextLine();
        return choice;
    }

    /**
     * Manages the hero's menu: display infos, modify hero, or play the game.
     */
    private void manageHero(Hero hero) throws InterruptedException {
        boolean play = false;
        while (!play) {
            try {
                switch (displayCharacterMenu()) {
                    case 1:
                        displayMessage(hero.toString());
                        break;
                    case 2:
                        hero.modify(heroManager.askType(), heroManager.askName());
                        db.editHeroes(hero);
                        break;
                    case 3:
                        game.prayForRespect();
                        break;
                    case 4:
                        play = true;
                        try {
                            difficultyMenu();
                            game.start();
                        } catch (Exception e) {
                            displayMessage("Erreur lors du lancement du jeu : " + e.getMessage());
                        }
                        break;
                    default:
                        displayMessage("Choix invalide !");
                }
            } catch (Exception e) {
                displayMessage("Erreur : " + e.getMessage());
            }
        }
    }

    /**
     * Displays the fight menu and returns the user's choice.
     */
    public int displayFightMenu() {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            try {
                String fightMenu = """
                        ===== Menu Combat =====
                        1. Attaquer
                        2. Fuir
                        """;
                displayMessage(fightMenu);
                choice = keyboard.nextInt();
                keyboard.nextLine();
                if (choice >= 1 && choice <= 2) {
                    valid = true;
                } else {
                    displayMessage("Veuillez entrer 1 ou 2.");
                }
            } catch (InputMismatchException e) {
                displayMessage("Entrée invalide. Veuillez entrer un nombre (1 ou 2).");
                keyboard.nextLine();
            }
        }
        return choice;
    }

    public String displayInventoryMenu() {
        int choice;
        boolean valid = false;
        String weaponType = null;

        while (!valid) {
            if (InventoryHandler.inventorySize() == 0) {
                displayMessage("Votre inventaire est vide.");
                break;
            } else if (InventoryHandler.inventorySize() == 1) {
                choice = 0;
                String selectedWeaponHeader = """
                        
                        |          ARME SÉLECTIONNÉE          |
                        """;
                displayMessage(selectedWeaponHeader);

                Object selectedItem = InventoryHandler.getItemFromInventory(choice);
                Weapon weapon = (Weapon) selectedItem;
                weaponType = weapon.getType();

                String selectedWeaponMessage = String.format("""
                        |   Vous avez choisi : %s             |
                        """, selectedItem.toString());
                displayMessage(selectedWeaponMessage);
                valid = true;

            } else {
                try {
                    // HEADER
                    InventoryHandler.showInventory();
                    String warriorStrengthChoices = """
                            
                            Epée +5
                            Massue +3
                            Arc +6 vs Dragons sinon +4
                            
                            """;

                    String magicianStrenghtChoices = """
                            
                            Boule de feu +7
                            Eclair +2
                            Invisibilité +8 contre les mauvais esprits sinon +5
                            
                            """;

                    if (Objects.equals(game.getHeroType(game.getHero()), "Warrior")) {
                        displayMessage(warriorStrengthChoices);
                    } else {
                        displayMessage(magicianStrenghtChoices);
                    }
                    // CHOICE
                    String choosePrompt = """
                            |          CHOISISSEZ UNE ARME        |
                            |                                     |
                            |   Entrez le numéro de l'arme que    |
                            |          vous souhaitez :           |
                            """;
                    displayMessage(choosePrompt);
                    choice = keyboard.nextInt() - 1;
                    keyboard.nextLine();

                    // SELECTED WEAPON
                    String selectedWeaponHeader = """
                            
                            |          ARME SÉLECTIONNÉE          |
                            """;
                    displayMessage(selectedWeaponHeader);

                    Object selectedItem = InventoryHandler.getItemFromInventory(choice);
                    Weapon weapon = (Weapon) selectedItem;
                    weaponType = weapon.getType();

                    String selectedWeaponMessage = String.format("""
                            |   Vous avez choisi : %s             |
                            """, selectedItem.toString());
                    displayMessage(selectedWeaponMessage);


                    if (choice >= 0 && choice <= 10) {
                        valid = true;
                    } else {
                        displayMessage("Veuillez un nombre entrer entre 0 et 10.");
                    }
                } catch (InputMismatchException e) {
                    displayMessage("Entrée invalide. Veuillez entrer un nombre (entre 0 et 10).");
                    keyboard.nextLine();
                }
            }
        }
        return weaponType;
    }

    /**
     * Displays a message to the player.
     */
    private void displayMessage(String message) {
        System.out.println(message);
    }
}
