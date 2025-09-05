package fr.dragonsslayer.board.dice;

import java.util.Random;

public class TwentyFacesDice implements Dice {
    @Override
    public int roll() {
        return new Random().nextInt(20)+1;
    }
}
