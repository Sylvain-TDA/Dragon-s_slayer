package fr.dragonsslayer.enemy;

public class Sorcerer extends Ennemy {
    public Sorcerer(String name, int lifePoints) {
        super("Sorcerer", name, lifePoints);
        this.setLife(9+lifePoints);
        this.setAttackLevel(2);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;

    }
}
