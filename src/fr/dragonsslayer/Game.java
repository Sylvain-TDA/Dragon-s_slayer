package fr.dragonsslayer;

import java.util.ArrayList;
import java.util.Random;

import fr.dragonsslayer.board.*;
import fr.dragonsslayer.characters.Hero;
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

    public Game() {
        voidText = "                 ";
        board = new ArrayList<>();
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
            int diceValue = 1;
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
            currentCell.interact();
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
        board.add(new EmptyCell(new Empty()));
        board.add(new EmptyCell(new Empty()));
        board.add(new EnnemyCell(new Sorcerer("Gildur")));
        board.add(new WeaponCell(new Club("Mace")));
        board.add(new WeaponCell(new Sword("Epée")));
        board.add(new WeaponCell(new Lightning("Eclairs")));
        board.add(new WeaponCell(new FireBall("Boule de feu")));
        board.add(new PotionCell(new Potion("Potion de soin",0)));
        board.add(new PotionCell(new Potion("Grande potion de soin",0)));
        board.add(new EnnemyCell(new Dragon("Hield")));
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
}
