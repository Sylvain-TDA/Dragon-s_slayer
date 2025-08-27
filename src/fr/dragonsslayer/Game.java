package fr.dragonsslayer;

import fr.dragonsslayer.HeroOutOfTheBoardException;

import java.util.Arrays;
import java.util.Random;

/**
 * This class handle the game in itself. I will start the game, initialize the board, display ennemies, rolling the dice...
 */

public class Game {
    private int playerPosition;
    private final String[] board = new String[64];
    private final int[] ennemiesPosition = new int[5];
    private final int[] chestPosition = new int[5];
    Menu menu = new Menu();
    String voidText = "                          ";

    /**
     * Initialize the dice and "roll it" by randomizing a number between 1 and 6.
     *
     * @return the result of the dice that have been rolled.
     */

    public int dice() {
        return new Random().nextInt(6) + 1;
    }

    /**
     * Start the game with a text introduction and the player's position at 0.
     * Then it rolls the dice and move the player.
     *
     * @return the position of the player
     */

    public int startingAGame() throws InterruptedException, HeroOutOfTheBoardException {
        playerPosition = 0;
        String intro = "                          " + "\n" +
                "Votre aventure débute..." + "\n" +
                "                          " + "\n" +
                "Vous êtes ensevelis sous un monticule de formes desquelles suintent un liquide étrange." + "\n" +
                "Après avoir réveillé vos yeux, ce liquide semble être le sang qui s'écoule des cadavres qui vous entourent." + "\n" +
                "Prenant votre courage à deux mains, vous dégagez celle d'un héro précédent pour vous mettre sur vos deux jambes, bien entières." + "\n" +
                "                          " + "\n" +
                "Votre position est : " + "\n" +
                playerPosition;
        menu.toString(intro);
        Thread.sleep(2000);

        while (true) {
            Thread.sleep(1000);
            int diceValue = dice();
            menu.toString(voidText + "Vous lancez le dé. Et vous faites : " + diceValue);
            Thread.sleep(700);

            playerPosition += diceValue;

            if (playerPosition > 64) {
                throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");
            } else if (playerPosition == 64) {
                String winGame = "Bravo, vous avez gagné !";
                menu.toString(winGame);
                return playerPosition;
            }

            String movingForward = "Vous avancez en case : " + playerPosition + voidText;
            menu.toString(movingForward);
        }
    }


    public int getPlayerPosition() {
        return playerPosition;
    }

    public String[] getBoard() {
        return board;
    }

    public int[] getEnnemyPosition() {
        return ennemiesPosition;
    }

    public int[] getChestPosition() {
        return chestPosition;
    }

    public void setBoard() {
        Arrays.fill(board, "empty");
    }

    public void setEnnemyPosition() {
        Random random = new Random();
        int count = 0;

        while (count < 10) {
            int pos = random.nextInt(board.length);
            if (board[pos].equals("empty")) {
                board[pos] = "enemy";
                count++;
            }
        }
    }

    public void setChestPosition() {
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
