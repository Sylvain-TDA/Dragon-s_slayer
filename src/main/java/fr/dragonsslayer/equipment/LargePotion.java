package fr.dragonsslayer.equipment;

/**
 * Subclass of Defensive equipment
 */

public class LargePotion extends DefensiveEquipment {
    public LargePotion(String name, String type, int defenseLevel) {
        super(name, type, defenseLevel);
    }

    public int getHealAmount () {
        return 5;
    }
}
