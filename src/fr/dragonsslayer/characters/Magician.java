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
        modify("Magician", name);
        }
}
