package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Game.RandomNameGenerator;

public class Bow extends Weapon{
    public Bow(String name, int attackLevel) {
        super(RandomNameGenerator.generateWeaponRandomName(), "Offensive Equipment", attackLevel);
    }
}
