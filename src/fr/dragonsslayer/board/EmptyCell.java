package fr.dragonsslayer.board;

import fr.dragonsslayer.Game;
import fr.dragonsslayer.characters.Hero;

public class EmptyCell extends Cell {
    public EmptyCell(Empty empty) {
        super(empty);
    }

    @Override
    public void interact(Hero hero, Game game) {
        System.out.println("Vous êtes sur une case vide");
    }

    @Override
    public String getType() {
        return "Empty";
    }
}
