package fr.dragonsslayer.equipment;

import fr.dragonsslayer.db.DataBaseHandling;
import fr.dragonsslayer.characters.Hero;
/**
 * Subclass of Defensive equipment
 */

public class Potion extends DefensiveEquipment {
    public Potion(String name, String type, int defenseLevel) {
        super(name, type, defenseLevel);
    }
}
