package fr.dragonsslayer.board;

import fr.dragonsslayer.Game.Game;
import fr.dragonsslayer.Hero.Hero;

public class EmptyCell extends Cell {
    public EmptyCell(Empty empty) {
        super(empty);
    }

    @Override
    public void interact(Hero hero, Game game) {
        System.out.println("""
    
    La pièce semble très sombre, mais vide. Vous relancez le dés.
    
    """);
    }

    @Override
    public String getType() {
        return "Empty";
    }
}
