package fr.dragonsslayer.equipment;

import fr.dragonsslayer.db.DataBaseHandling;
import fr.dragonsslayer.characters.Hero;

/**
 * Subclass of Defensive equipment
 */

public class Potion extends DefensiveEquipment {

    public Potion(String name, int defenseLevel) {
        super(name, "Potion", 0);
    }

    public int getHealAmount () {
        return 2;
    }
}
