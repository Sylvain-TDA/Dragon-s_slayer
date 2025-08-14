package fr.dragonsslayer.equipment;

public class OffensiveEquipment {
    private String type;
    private String name;
    private int attackLevel;

    public OffensiveEquipment(String name, String type) {
        this.name = name;
        this.type = type;
        setAttackLevel();
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

    public void setAttackLevel() {
        if (this.type != null && this.type.equals("Sword")) {
            this.attackLevel = 5;
        } else if(this.type != null && this.type.equals("Club")) {
            this.attackLevel = 3;
        } else if(this.type != null && this.type.equals("FireBall")) {
            this.attackLevel = 7;
        } else if(this.type != null && this.type.equals("Lightning")) {
            this.attackLevel = 2;
        }
    }

    public String toString() {
        return "OffensiveEquipment{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
               ", attackLevel=" + attackLevel +
                '}';
    }
}
