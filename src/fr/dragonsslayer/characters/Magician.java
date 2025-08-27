package fr.dragonsslayer.characters;

public class Magician extends Hero {
    /**
     * Creating the hero.
     *
     * @param type choosing the type of the hero.
     * @param name choosing the name of the hero.
     */
    public Magician(String type, String name) {
        super("Magician", name);
        setLife(6);
        setAttackLevel(8);
        setWeapon("spell");
    }

    public void modify(String type, String name) {
        setType(type);
        setName(name);
        setLife(6);
        setAttackLevel(8);
        setWeapon("spell");
    }
}
