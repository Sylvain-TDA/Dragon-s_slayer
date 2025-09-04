package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Game.RandomNameGenerator;

public class Bow extends Weapon{
    public Bow(String name) {
        super(RandomNameGenerator.generateWeaponRandomName(), "Arc");
    }
}
