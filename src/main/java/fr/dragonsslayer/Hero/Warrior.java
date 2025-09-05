package fr.dragonsslayer.Hero;

public class Warrior extends Hero {
    /**
     * Creating the hero.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */
    public Warrior(String type, String name) {
        super("Warrior", name);
        modify("Warrior", name);
        setAttackLevel(5);
        setLife(10);
    }
}
