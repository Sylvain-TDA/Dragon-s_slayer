package fr.dragonsslayer.equipment;

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
