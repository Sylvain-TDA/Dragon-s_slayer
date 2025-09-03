package fr.dragonsslayer.ennemy;

import fr.dragonsslayer.characters.Hero;

public class Goblin extends Hero {
    public Goblin(String name) {
        super("Goblin", name);
        this.setLife(6);
        this.setAttackLevel(1);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;

    }
}
