package fr.dragonsslayer.Hero;

public class Magician extends Hero {
    /**
     * Creating the Magician.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */

    public Magician(String type, String name) {
        super("Magician", name);
        modify("Magician", name);
        setAttackLevel(8);
        setLife(6);
    }
}
