package fr.dragonsslayer;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import fr.dragonsslayer.board.*;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.characters.Magician;
import fr.dragonsslayer.characters.Warrior;
import fr.dragonsslayer.ennemy.*;
import fr.dragonsslayer.equipment.*;
import fr.dragonsslayer.db.DataBaseHandling;

/**
 * This class handle the game in itself. I will start the game, initialize the board, display ennemies, rolling the dice...
 */

public class Game {
    private int playerPosition;
    private final ArrayList<Cell> board;
    public String voidText;
    private Hero hero;
    private boolean hadPray;
    private final Scanner keyboard;
    private DataBaseHandling db;

    public Game() {
        voidText = "                 ";
        board = new ArrayList<>();
        this.keyboard = new Scanner(System.in);
        this.db = new DataBaseHandling();
    }


    /**
     * Initialize the dice and "roll it" by randomizing a number between 1 and 6.
     *
     * @return the result of the dice that have been rolled.
     */

    private int dice() {
        return new Random().nextInt(6) + 1;
    }

    /**
     * Start the game with a text introduction and the player's position at 0.
     * It also sets the board as it is necessary at the beginning of a game.
     *
     * @throws InterruptedException if the thread is interrupted during the game.
     */

    protected void startingAGame() throws InterruptedException {
        playerPosition = 0;
        initBoard();
        String intro = voidText + "\n" + "Votre aventure débute..." + "\n" + voidText + "\n" + "Vous êtes ensevelis sous un monticule de formes desquelles suintent un liquide étrange." + "\n" + "Après avoir réveillé vos yeux, ce liquide semble être le sang qui s'écoule des cadavres qui vous entourent." + "\n" + "Prenant votre courage à deux mains, vous dégagez celle d'un héro précédent pour vous mettre sur vos deux jambes, bien entières." + "\n" + voidText + "\n" + "Votre position est : " + "\n" + playerPosition;
        System.out.println(intro);
        Thread.sleep(700);
    }

    /**
     * Handle the game after is as begun.
     * It rolls the dice, move the player and handle the encounter on specific cells.
     *
     * @throws HeroOutOfTheBoardException if the hero goes out of the board.
     */

    protected int playingTheGame() throws HeroOutOfTheBoardException {
        while (playerPosition != 10) {
            int diceValue = dice();
            playerPosition += diceValue;
            System.out.println(voidText + "Vous lancez le dé. Et vous faites : " + diceValue + """
                    
                    """);
            String movingForward = """
                    Vous avancez en case :\s""" + playerPosition + """
                       \s
                    \s""";
            System.out.println(movingForward);

            if (playerPosition > 10) {
                throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");
            } else if (playerPosition == 10) {
                System.out.println("Bravo, vous avez gagné !");
                return playerPosition;
            }
            Cell currentCell = board.get(playerPosition);
            currentCell.interact(hero);
        }
        return playerPosition;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public void getEnnemyPosition() {
        System.out.println(board.indexOf("Ennemy"));
    }

    public void getChestPosition() {
        System.out.println(board.indexOf("Chest"));
    }

    /**
     * Handle the initialization of the board.
     */

    public void initBoard() {

        // Dragon's creation

        board.set(45, new EnnemyCell(new Dragon("Hield")));
        board.set(52, new EnnemyCell(new Dragon("Hield")));
        board.set(56, new EnnemyCell(new Dragon("Hield")));
        board.set(62, new EnnemyCell(new Dragon("Hield")));

        // Sorcerer's creation

        board.set(10, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(20, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(25, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(32, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(35, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(36, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(37, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(40, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(44, new EnnemyCell(new Sorcerer("Gildur")));
        board.set(47, new EnnemyCell(new Sorcerer("Gildur")));

        // Goblin's creation

        board.set(3, new EnnemyCell(new Goblin("Wierk")));
        board.set(6, new EnnemyCell(new Goblin("Wierk")));
        board.set(9, new EnnemyCell(new Goblin("Wierk")));
        board.set(12, new EnnemyCell(new Goblin("Wierk")));
        board.set(15, new EnnemyCell(new Goblin("Wierk")));
        board.set(18, new EnnemyCell(new Goblin("Wierk")));
        board.set(21, new EnnemyCell(new Goblin("Wierk")));
        board.set(24, new EnnemyCell(new Goblin("Wierk")));
        board.set(27, new EnnemyCell(new Goblin("Wierk")));
        board.set(30, new EnnemyCell(new Goblin("Wierk")));

        // Club's creation

        board.set(2, new WeaponCell(new Club("Mace")));
        board.set(11, new WeaponCell(new Club("Mace")));
        board.set(5, new WeaponCell(new Club("Mace")));
        board.set(22, new WeaponCell(new Club("Mace")));
        board.set(38, new WeaponCell(new Club("Mace")));

        // Sword's creation

        board.set(19, new WeaponCell(new Sword("Epée")));
        board.set(26, new WeaponCell(new Sword("Epée")));
        board.set(42, new WeaponCell(new Sword("Epée")));
        board.set(53, new WeaponCell(new Sword("Epée")));

        // Lightning's spell's creation

        board.set(1, new WeaponCell(new Lightning("Eclairs")));
        board.set(4, new WeaponCell(new Lightning("Eclairs")));
        board.set(8, new WeaponCell(new Lightning("Eclairs")));
        board.set(17, new WeaponCell(new Lightning("Eclairs")));
        board.set(23, new WeaponCell(new Lightning("Eclairs")));

        // Fireball's spell's creation

        board.set(48, new WeaponCell(new FireBall("Boule de feu")));
        board.set(49, new WeaponCell(new FireBall("Boule de feu")));

        // Potion's creation

        board.set(7, new PotionCell(new Potion("Potion de soin", 2)));
        board.set(13, new PotionCell(new Potion("Potion de soin", 2)));
        board.set(31, new PotionCell(new Potion("Potion de soin", 2)));
        board.set(33, new PotionCell(new Potion("Potion de soin", 2)));
        board.set(39, new PotionCell(new Potion("Potion de soin", 2)));
        board.set(43, new PotionCell(new Potion("Potion de soin", 2)));

        // LargePotion's creation

        board.set(28, new LargePotionCell(new LargePotion("Grande potion de soin", "Potion", 5)));
        board.set(41, new LargePotionCell(new LargePotion("Grande potion de soin", "Potion", 5)));

        // Empty cell's creation

        board.set(0, new EmptyCell(new Empty()));
        board.set(14, new EmptyCell(new Empty()));
        board.set(16, new EmptyCell(new Empty()));
        board.set(19, new EmptyCell(new Empty()));
        board.set(29, new EmptyCell(new Empty()));
        board.set(34, new EmptyCell(new Empty()));
        board.set(46, new EmptyCell(new Empty()));
        board.set(50, new EmptyCell(new Empty()));
        board.set(51, new EmptyCell(new Empty()));
        board.set(54, new EmptyCell(new Empty()));
        board.set(55, new EmptyCell(new Empty()));
        board.set(57, new EmptyCell(new Empty()));
        board.set(58, new EmptyCell(new Empty()));
        board.set(59, new EmptyCell(new Empty()));
        board.set(60, new EmptyCell(new Empty()));
        board.set(61, new EmptyCell(new Empty()));
        board.set(63, new EmptyCell(new Empty()));
    }

    private void showBoard() {
        System.out.println(board);
    }

    /**
     * If the player choose to get the name of old players his life will be increase.
     * Display a message that indicate that.
     */

    protected void prayForRespect() {
        DataBaseHandling db = new DataBaseHandling();
        ArrayList<String> dbHeroes = db.getHeroes();
        System.out.println("Vous avez prié : " + dbHeroes);
        if (!hadPray) {
            db.changeLifePoints(hero);
            System.out.println("""
                    
                    Vous avez honorez ceux qui sont passés par là avant vous.Vous vous sentez plus revigoré.
                    Voir votre nom semble être prémonitoire et vous laisse un sentiment étrange...
                    
                    \s""");
        } else {
            System.out.println("Les mort n'apprécient pas la triche.");
        }
        hadPray = true;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
