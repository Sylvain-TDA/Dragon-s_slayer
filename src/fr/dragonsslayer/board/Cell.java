package fr.dragonsslayer.board;

import fr.dragonsslayer.Game;
import fr.dragonsslayer.characters.Hero;

public abstract class Cell {
    private Object content;

    public Cell(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public abstract String getType();

    public void interact(Hero hero, Game game){
    };
}
