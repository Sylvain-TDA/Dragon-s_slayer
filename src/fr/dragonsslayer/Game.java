package fr.dragonsslayer;

import java.util.Arrays;
import java.util.Random;
import java.lang.Thread;

/**
 *  This class handle the game in itself. I will start the game, initialize the board, display ennemies, rolling the dice...
 */

public class Game {
    private int playerPosition;
    private String[] board = new String[64];
    private int[] ennemiesposition = new int[5];
    private int[] chestPosition = new int[5];

    /**
     * Initialize the dice and "roll it" by randomizing a number between 1 and 6.
     * @return the result of the dice that have been rolled.
     */

    public int Dice() {
        int dice = 0;
        Random randomObj = new Random();
        dice = randomObj.nextInt(6) + 1;
        return dice;
    }

    /**
     * Start the game with a text introduction and the player's position at 0.
     * Then it rolls the dice and move the player.
     * @return the position of the player
     */

    public int startingAGame()  throws InterruptedException {
        playerPosition = 0;
        String intro = "                          " + "\n" +
                "Votre aventure débute..." + "\n" +
                "                          " + "\n" +
                "Vous êtes ensevelis sous un monticule de formes desquelles suintent un liquide étrange." + "\n" +
                "Après avoir réveillé vos yeux, ce liquide semble être le sang qui s'écoule des cadavres qui vous entourent." + "\n" +
                "Prenant votre courage à deux mains, vous dégagez celle d'un héro précédent pour vous mettre sur vos deux jambes, bien entières." +  "\n" +
                "                          " +  "\n" +
                "Votre position est : " + "\n" +
                + playerPosition;
        System.out.println(intro);
        Thread.sleep(2000);

        while (playerPosition < 64) {
            Thread.sleep(1000);
            int diceValue = Dice();
            System.out.println("                          ");
            System.out.println("Vous lancez le dé. Et vous faites : " + diceValue);
            Thread.sleep(700);

            playerPosition = playerPosition + diceValue;
            System.out.println("Vous avancez en case : " + playerPosition);
            System.out.println("                          ");
        }

        if (playerPosition >= 64) {
            Thread.sleep(1000);
            System.out.println("Bravo, vous avez gagné!");
        }

        return playerPosition;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public String[] getBoard() {
        return board;
    }

    public int[] getEnnemiesposition() {
        return ennemiesposition;
    }

    public int[] getChestPosition() {
        return chestPosition;
    }

    public void setBoard() {
        Arrays.fill(board, "empty");
    }

    public void setEnnemiesposition() {
        Random random = new Random();
        int count = 0;

        while (count < 10) {
            int pos = random.nextInt(board.length);
            if (board[pos] == null) {
                board[pos] = "enemy";
                count++;
            }
        }
    }

    public void setChestposition() {
        Random random = new Random();
        int count = 0;

        while (count < 15) {
            int pos = random.nextInt(board.length);
            if (board[pos] == null) {
                board[pos] = "chest";
                count++;
            }
        }
    }
}
