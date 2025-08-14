package fr.dragonsslayer;

import java.util.Arrays;
import java.util.Random;

public class Game {
    private int playerPosition;
    private String[] board = new String[64];
    private int[] ennemiesposition = new int[5];
    private int[] chestPosition = new int[5];

    public int Dice() {
        int dice = 0;
        Random randomObj = new Random();
        dice = randomObj.nextInt(6) + 1;
        return dice;
    }

    public void startingAGame() {
        playerPosition = 0;
        System.out.println("Votre aventure débute...");
        System.out.println("Comme tous les jeux, vous commencer au début.");
        System.out.println("Votre position est : " + playerPosition);
        int diceValue = Dice();
        System.out.println("Vous lancez le dé. Et vous faites : " + diceValue);
        playerPosition = playerPosition + diceValue;
        System.out.println("Vous avancez en case : " + playerPosition);

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
