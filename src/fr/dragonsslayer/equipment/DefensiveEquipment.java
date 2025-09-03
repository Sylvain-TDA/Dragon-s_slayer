package fr.dragonsslayer.equipment;

import fr.dragonsslayer.board.Cell;
import fr.dragonsslayer.characters.Hero;

/**
 * Super class to define defensive equipment
 */

public abstract class DefensiveEquipment {
    protected String type;
    private String name;
    protected int DefenseLevel;

    public DefensiveEquipment(String name, String type, int defenseLevel) {
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getDefenseLevel() {
        return DefenseLevel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefenseLevel(int defenseLevel) {
        this.DefenseLevel = defenseLevel;
    }

    public String toString() {
        return "type='" + type + '\'' +
                ", nom='" + name + '\'' +
                ", Niveau de defense=" + DefenseLevel +
                '}';
    }

    public int heal(Hero hero, int amount) {
        int lifePoints = hero.getLife();
        lifePoints += amount;

        return lifePoints;
    }
}
