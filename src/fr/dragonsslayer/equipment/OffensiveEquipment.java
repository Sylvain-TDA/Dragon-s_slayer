package fr.dragonsslayer.equipment;

import fr.dragonsslayer.board.Cell;

/**
 * Super class to define offensive equipment
 */

public abstract class OffensiveEquipment {
    private String type;
    private String name;
    private int attackLevel;

    public OffensiveEquipment(String name, String type, int attackLevel) {
        this.name = name;
        this.type = type;
        setAttackLevel(attackLevel);
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

    public void setAttackLevel(int attackLevel) {
      this.attackLevel = attackLevel;
    }

    public String toString() {
        return "type='" + type + '\'' +
                ", nom='" + name + '\'' +
               ", Niveau d'attaque=" + attackLevel +
                '}';
    }
}
