package fr.dragonsslayer.enemy;

public class Sorcerer extends Ennemy {
    public Sorcerer(String name) {
        super("Sorcerer", name);
        this.setLife(9);
        this.setAttackLevel(20);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;

    }
}
