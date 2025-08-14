package fr.dragonsslayer.equipment;

public class DefensiveEquipment {
    private String type;
    private String name;
    private int DefenseLevel;

    public DefensiveEquipment(String name, String type) {
        this.name = name;
        this.type = type;
        setDefenseLevel();
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

    public void setDefenseLevel() {
        if (this.type != null && this.type.equals("Shield")) {
            this.DefenseLevel = 1;
        } else if(this.type != null && this.type.equals("Potion")) {
            this.DefenseLevel = 2;
        }
    }

    public String toString() {
        return "DefensiveEquipment{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", DefenseLevel=" + DefenseLevel +
                '}';
    }
}
