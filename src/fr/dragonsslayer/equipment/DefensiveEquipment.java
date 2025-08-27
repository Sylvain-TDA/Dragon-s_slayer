package fr.dragonsslayer.equipment;

public abstract class DefensiveEquipment {
    private String type;
    private String name;
    private int DefenseLevel;

    public DefensiveEquipment(String name, String type, int defenseLevel) {
        this.name = name;
        this.type = type;
        setDefenseLevel(defenseLevel);
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
        return "DefensiveEquipment{" +
                "type='" + type + '\'' +
                ", nom='" + name + '\'' +
                ", Niveau de defense=" + DefenseLevel +
                '}';
    }
}
