package fr.dragonsslayer.characters;

public class Hero {

    private String type;
    private String name;
    private int life;
    private int attackLevel;
    private String weapon;

    public Hero(String type, String name) {
        this.type = type;
        this.name = name;
        setLife();
        setAttackLevel();
        setWeapon(weapon);
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setLife() {
        if (this.type != null && this.type.equals("Warrior")) {
            this.life = 10;
        } else {
            this.life = 6;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttackLevel() {
        if (this.type != null && this.type.equals("Warrior")) {
            this.attackLevel = 5;
        } else {
            this.attackLevel = 8;
        }
    }

    public void setWeapon(String weapon) {
        if (this.type != null && this.type.equals("Warrior")) {
            this.weapon = "weapon";
        } else {
            this.weapon = "spell";
        }
    }

    public String toString() {
        return "Hero{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", life=" + life +
                ", attackLevel=" + attackLevel +
                ", weapon='" + weapon + '\'' +
                '}';
    }

}



