package fr.dragonsslayer.characters;

public class Warrior extends Hero {
    /**
     * Creating the hero.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */
    public Warrior(String type, String name) {
    super("Warrior", name);
        setLife(10);
        setAttackLevel(5);
        setWeapon("weapon");
    }

    public void modify(String type, String name) {
        setType(type);
        setName(name);
        setLife(10);
        setAttackLevel(5);
        setWeapon("weapon");
    }

}
