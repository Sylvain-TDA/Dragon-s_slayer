package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Game.RandomNameGenerator;

/**
 * Subclass of Offensive equipment
 */

public class Lightning extends Spell {
    public Lightning(String name) {
        super(RandomNameGenerator.generateWeaponRandomName(), "Eclair");
    }
}
