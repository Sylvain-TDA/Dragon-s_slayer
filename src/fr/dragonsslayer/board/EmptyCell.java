package fr.dragonsslayer.board;

import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.Potion;
import fr.dragonsslayer.board.Empty;

public class EmptyCell extends Cell {
    public EmptyCell(Empty empty) {
        super(empty);
    }

    @Override
    public void interact(Hero hero) {
        System.out.println("Vous êtes sur une case vide");
    }

    @Override
    public String getType() {
        return "Empty";
    }
}
