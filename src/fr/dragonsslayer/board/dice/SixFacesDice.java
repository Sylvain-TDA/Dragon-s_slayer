package fr.dragonsslayer.board.dice;

import java.util.Random;

public class SixFacesDice implements Dice {
    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}

