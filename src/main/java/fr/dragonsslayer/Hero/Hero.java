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
    private static final int[] XP_THRESHOLDS = {0, 30, 50, 70, 90};
    private static final double[] LIFE_MULTIPLIERS = {1.0, 1.2, 1.4, 1.6, 1.8};
    private static final double[] ATTACK_MULTIPLIERS = {1.0, 1.2, 1.4, 1.6, 1.0};
    private int baseLife;
    private int baseAttackLevel;

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

    public int getLevel() {
        return level;
    }

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

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    protected void setOffensiveEquipment(String offensiveEquipment) {
        this.OffensiveEquipment = offensiveEquipment;
    }

    protected void setDefensiveEquipment(String defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    /**
     * Store the xp amount in the Hero's class
     * @param xp to be added to the xp amount
     */

    public void storeXp(int xp) {
        this.xp += xp;
        System.out.println("""
                ******************
                Vous avez gagné :
                """ + xp + " xp.");
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

    /**
     * Set defaults attributes
     * @param type the determine the default hero type.
     */

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
        String BOLD = "\u001B[1m";
        String RESET = "\u001B[0m";

        return BOLD + "nom : '" + name + "'" + RESET + "\n" +
                "classe : '" + type + "'\n" +
                "points de vie : " + life + "\n" +
                "niveau d'attaque : " + attackLevel + "\n" +
                "arme : '" + OffensiveEquipment + "'\n";
    }

    /**
     * Handle the display when the hero level up
     * @param level to be display
     */

    private void displayLevelUpMessage(int level) {
        System.out.printf("""
                Vous êtes niveau %d.
                ******************
                Points de vie : %d
                Points d'attaque : %d
                """, level, this.life, this.attackLevel);
    }

    /**
     * Set the new level of the hero
     * @param xp to set the new level
     */

    public void setLevel(int xp) {
        this.xp = xp;
        int newLevel = calculateLevel(xp);

        if (newLevel != this.level) {
            this.level = newLevel;
            updateStats(newLevel);
            displayLevelUpMessage(newLevel);
        }
    }

    /**
     * Calculate if the hero go to a new level
     * @param xp that determine if the hero level up or not
     * @return the level or 1 (initial level)
     */
    private int calculateLevel(int xp) {
        for (int i = XP_THRESHOLDS.length - 1; i >= 0; i--) {
            if (xp >= XP_THRESHOLDS[i]) {
                return i;
            }
        }
        return 1;
    }

    /**
     * Update the stats depending on the level
     * @param level that determine the stats
     */

    private void updateStats(int level) {
        if (this.level == 1) {
            this.baseLife = this.life;
            this.baseAttackLevel = this.attackLevel;
        }

        this.life = (int) Math.ceil(this.baseLife * LIFE_MULTIPLIERS[level]);
        this.attackLevel = (int) Math.ceil(this.baseAttackLevel * ATTACK_MULTIPLIERS[level]);
    }

}



