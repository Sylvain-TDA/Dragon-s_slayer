package fr.dragonsslayer.characters;

/**
 * The class is handling the hero : name, type, life, attack level and weapon.
 */

public abstract class Hero {

    private String type;
    private String name;
    private int life;
    protected int attackLevel;
    protected String weapon;

    /**
     * Creating the hero.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */

    public Hero(String type, String name) {
        this.type = type;
        this.name = name;
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

    public void setLife(int life) {
                   this.life = life;
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

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    /**
     * Method to modify the hero by changing the name or the type.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */


    public void modify(String type, String name) {
        this.name = name;
        setTypeAndDefaults(type); // <-- réapplique les stats en fonction du type
    }

    private void setTypeAndDefaults(String type) {
        if (type.equalsIgnoreCase("Warrior")) {
            this.type = "Warrior";
            this.life = 10;
            this.attackLevel = 5;
            this.weapon = "weapon";
        } else if (type.equalsIgnoreCase("Magician")) {
            this.type = "Magician";
            this.life = 6;
            this.attackLevel = 8;
            this.weapon = "spell";
        }
    }

    /**
     * Allow to print the hero's information
     *
     * @return cutomize the hero's information
     */

    public String toString() {
        return "Hero{" + "\n" +
                "classe : '" + type + '\'' + "\n" +
                "nom : '" + name + '\'' + "\n" +
                "points de vie : " + life + "\n" +
                "niveau d'attaque : " + attackLevel + "\n" +
                "arme :'" + weapon + '\'' + "\n" +
                '}';
    }

}



