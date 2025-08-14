package fr.dragonsslayer;

import java.util.Random;

public class Game {
    private int playerPosition;
    private int[] board = new int[64];
    private int[] ennemiesposition = new int[5];
    private int[] chestPosition = new int[5];

    public int Dice() {
        int dice = 0;
        Random randomObj = new Random();
        dice = randomObj.nextInt(6) + 1;
        return dice;
    }
}
