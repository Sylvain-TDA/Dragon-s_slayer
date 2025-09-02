package fr.dragonsslayer.characters;

/**
 * The class is handling the hero : name, type, life, attack level and weapon.
 */

public abstract class Hero {

    private String type;
    private String name;
    private int life;
    private int attackLevel;
    private String OffensiveEquipment;
    private String defensiveEquipment;

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

    public String getOffensiveEquipment() {
        return OffensiveEquipment;
    }

    public String getDefensiveEquipment() {
        return defensiveEquipment;
    }

    protected void setLife(int life) {
        this.life = life;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    protected void setOffensiveEquipment(String offensiveEquipment) {
        this.OffensiveEquipment = offensiveEquipment;
    }

    protected void setDefensiveEquipment(String defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    /**
     * Method to modify the hero by changing the name or the type.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */

    public void modify(String type, String name) {
        this.name = name;
        setTypeAndDefaults(type);
    }

    private void setTypeAndDefaults(String type) {
        if (type.equalsIgnoreCase("Warrior")) {
            this.type = "Warrior";
            this.life = 10;
            this.attackLevel = 5;
            this.OffensiveEquipment = "weapon";
            this.defensiveEquipment = "shield";
        } else if (type.equalsIgnoreCase("Magician")) {
            this.type = "Magician";
            this.life = 6;
            this.attackLevel = 8;
            this.OffensiveEquipment = "spell";
            this.defensiveEquipment = "shield";
        }
    }

    /**
     * Allow to print the hero's information
     *
     * @return cutomize the hero's information
     */

    public String toString() {
        return "nom : '" + name + '\'' + "\n" +
                "classe : '" + type + '\'' + "\n" +
                "points de vie : " + life + "\n" +
                "niveau d'attaque : " + attackLevel + "\n" +
                "arme :'" + OffensiveEquipment + '\'' + "\n";
    }

}



