package fr.dragonsslayer.board.dice;

import java.util.Random;

/**
 * Roll the dice and display the result between 1 and 6.
 */

public class SixFacesDice implements Dice {
    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}

