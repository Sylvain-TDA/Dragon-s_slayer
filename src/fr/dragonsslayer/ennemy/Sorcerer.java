package fr.dragonsslayer.ennemy;

import fr.dragonsslayer.characters.Hero;

public class Sorcerer extends Hero {
    public Sorcerer(String name) {
        super("Sorcerer", name);
        this.setLife(9);
        this.setAttackLevel(2);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;

    }
}
