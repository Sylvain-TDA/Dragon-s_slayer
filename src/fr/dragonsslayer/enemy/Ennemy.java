package fr.dragonsslayer.enemy;

import fr.dragonsslayer.Hero.Hero;

public class Ennemy extends Hero {
    public Ennemy(String name, String type) {
        super(name, type);
    }

    @Override
    public String toString() {
        return "nom : '" + getName() + '\'' + "\n" +
                "classe : '" + getType() + '\'' + "\n" +
                "points de vie : " + getLife() + "\n" +
                "niveau d'attaque : " + getAttackLevel() + "\n";
    }
}
