package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Game.RandomNameGenerator;

/**
 * Subclass of Offensive equipment
 */

public class Club extends Weapon {
    public Club(String name) {
        super(RandomNameGenerator.generateWeaponRandomName(), "Offensive Equipment", 3);
    }
}
