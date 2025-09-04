package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Game.RandomNameGenerator;

/**
 * Subclass of Offensive equipment
 */

public class Sword extends Weapon {
    public Sword(String name) {
        super(RandomNameGenerator.generateWeaponRandomName(), "Epée");
    }
}
