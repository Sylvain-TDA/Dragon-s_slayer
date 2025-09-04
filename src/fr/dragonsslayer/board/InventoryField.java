package fr.dragonsslayer.board;

import fr.dragonsslayer.Game.Game;
import fr.dragonsslayer.Hero.Hero;

public class InventoryField extends Cell {
    public InventoryField(Inventory empty) {
        super(empty);
    }

    @Override
    public String getType() {
        return "Empty";
    }
}
