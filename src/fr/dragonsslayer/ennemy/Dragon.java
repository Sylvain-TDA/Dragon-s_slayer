package fr.dragonsslayer.ennemy;

import fr.dragonsslayer.characters.Hero;

public class Dragon extends Hero {
    public Dragon(String name) {
        super("Dragon", name);
        this.setLife(15);
        this.setAttackLevel(4);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;

    }
}
