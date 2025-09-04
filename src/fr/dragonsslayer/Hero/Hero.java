package fr.dragonsslayer.Hero;

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
    private int level;
    private int xp;

    /**
     * Creating the hero.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */

    public Hero(String type, String name) {
        this.type = type;
        this.name = name;
        this.level = 0;
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

    public int getLevel() {return level;}

    public String getOffensiveEquipment() {
        return OffensiveEquipment;
    }

    public String getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public int getXp() {
        return xp;
    }

    public void setLife(int life) {
        this.life = life;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        if (xp < 50) {
            this.level = 1;
            System.out.println("Vous êtes niveau 1.");
        } else if (xp > 50 && xp < 70) {
            this.level = 2;
            System.out.println("Vous êtes niveau 2.");
        } else if (xp > 70 && xp > 90 ) {
            this.level = 3;
            System.out.println("Vous êtes niveau 3.");
        } else if (xp > 90 && xp < 100) {
            this.level = 4;
            System.out.println("Vous êtes niveau 4.");
        } else if(xp > 100) {
            this.level = 5;
            System.out.println("Vous êtes niveau 5.");
        }
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    protected void setOffensiveEquipment(String offensiveEquipment) {
        this.OffensiveEquipment = offensiveEquipment;
    }

    protected void setDefensiveEquipment(String defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    public void storeXp(int xp) {
        this.xp += xp;
        System.out.println("Vous avez gagné :" + xp + " xp.");
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



