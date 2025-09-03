package fr.dragonsslayer;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import fr.dragonsslayer.board.*;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.characters.Magician;
import fr.dragonsslayer.characters.Warrior;
import fr.dragonsslayer.enemy.*;
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

    public int criticDice() {
        return new Random().nextInt(20) + 1;
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
        while (playerPosition != 64) {
            int diceValue = dice();
            playerPosition += diceValue;
            System.out.println(voidText + "Vous lancez le dé. Et vous faites : " + diceValue + """
                    
                    """);
            String movingForward = """
                    Vous avancez en case :\s""" + playerPosition + """
                       \s
                    \s""";
            System.out.println(movingForward);

            if (playerPosition > 64) {
                playerPosition = 64;
                throw new HeroOutOfTheBoardException("Bravo, vous avez gagné");
            } else if (playerPosition == 64) {
                System.out.println("Bravo, vous avez gagné !");
                return playerPosition;
            }
            Cell currentCell = board.get(playerPosition);
            currentCell.interact(hero, this);
        }
        return playerPosition;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public void getEnemyPosition() {
        System.out.println(board.indexOf("Enemy"));
    }

    public void getChestPosition() {
        System.out.println(board.indexOf("Chest"));
    }

    /**
     * Handle the initialization of the board.
     */

    public void initBoard() {

        for (int i = 0; i <= 64; i++) {
            board.add(new EmptyCell(new Empty()));
        }

        // Placement des entités
        placeRandomly(new EnemyCell(new Dragon("Hield")), 4);
        placeRandomly(new EnemyCell(new Sorcerer("Hield")), 10);
        placeRandomly(new EnemyCell(new Goblin("Hield")), 10);

        placeRandomly(new WeaponCell(new Club("Hield")), 5);
        placeRandomly(new WeaponCell(new Sword("Hield")), 4);
        placeRandomly(new WeaponCell(new Lightning("Hield")), 5);
        placeRandomly(new WeaponCell(new FireBall("Hield")), 2);

        placeRandomly(new PotionCell(new Potion("Potion de soin", 2)), 6);
        placeRandomly(new LargePotionCell(new LargePotion("Grande potion de soin", "Potion", 5)), 2);
    }

    /**
     * Initialize a random position in the board to fill it
     *
     * @param newCell new Cell type
     * @param count   number of cell to be placed
     */

    private void placeRandomly(Cell newCell, int count) {
        Random rand = new Random();
        int size = board.size();

        for (int i = 0; i < count; i++) {
            int pos;
            do {
                pos = rand.nextInt(size);
            } while (!(board.get(pos) instanceof EmptyCell));
            board.set(pos, newCell);
        }
    }

    private void showBoard() {
        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i));
        }
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

    public void updatedPlayerPosition(int randomNumber) {
        this.playerPosition = playerPosition - randomNumber;
        if (playerPosition - randomNumber < 0) {
            this.playerPosition = 0;
        }
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
