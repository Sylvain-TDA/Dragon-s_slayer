package fr.dragonsslayer.equipment;

import fr.dragonsslayer.Hero.Hero;

/**
 * Super class to define offensive equipment
 */

public abstract class OffensiveEquipment {
    private String type;
    private String name;
    private int attackLevel;

    public OffensiveEquipment(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int setAttackLevel(Hero hero, int attackLevel) {
        int strength = hero.getAttackLevel();
        strength += attackLevel;
        return strength;
    }

    public String toString() {
        return "type='" + type + '\'' +
                ", nom='" + name + '\'' +
                ", Niveau d'attaque=" + attackLevel +
                '}';
    }
}
