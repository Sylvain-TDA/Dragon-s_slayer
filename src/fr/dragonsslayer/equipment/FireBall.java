package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Game.RandomNameGenerator;

/**
 * Subclass of Offensive equipment
 */

public class FireBall extends Spell{
    public FireBall(String name) {
        super(RandomNameGenerator.generateWeaponRandomName(), "Boule de feu", 7);
    }
}
