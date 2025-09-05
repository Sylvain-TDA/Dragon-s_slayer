package fr.dragonsslayer.board.dice;

import java.util.Random;

/**
 * Roll the dice and display the result between 1 and 20.
 */

public class TwentyFacesDice implements Dice {
    @Override
    public int roll() {
        return new Random().nextInt(20)+1;
    }
}
