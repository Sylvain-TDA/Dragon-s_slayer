package fr.dragonsslayer.board;

/**
 * Specific class to handle message exception if the player falls out of the board.
 */

public class HeroOutOfTheBoardException extends Exception {
    public HeroOutOfTheBoardException(String message) {
        super(message);
    }
}
