package fr.dragonsslayer.characters;

/**
 * The class is handling the hero : name, type, life, attack level and weapon.
 */

public class Hero {

    private String type;
    private String name;
    private int life;
    private int attackLevel;
    private String weapon;

    /**
     * Creating the hero.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */

    public Hero(String type, String name) {
        this.type = type;
        this.name = name;
        setLife();
        setAttackLevel();
        setWeapon();
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

    public void setWeapon() {
        if (this.type != null && this.type.equals("Warrior")) {
            this.weapon = "weapon";
        } else {
            this.weapon = "spell";
        }
    }

    /**
     * Method to modify the hero by changing the name or the type.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */

    public void modify(String type, String name) {
        this.type = type;
        this.name = name;
        setType(type);
        setName(name);
    }

    /**
     * Allow to print the hero's information
     *
     * @return cutomize the hero's information
     */

    public String toString() {
        return "Hero{" + "\n" +
                "type='" + type + '\'' + "\n" +
                "name='" + name + '\'' + "\n" +
                "life=" + life + "\n" +
                "attackLevel=" + attackLevel + "\n" +
                "weapon='" + weapon + '\'' + "\n" +
                '}';
    }

}



