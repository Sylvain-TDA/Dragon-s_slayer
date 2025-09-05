package fr.dragonsslayer.enemy;

public class Goblin extends Ennemy {
    public Goblin(String name, int lifePoints) {
        super("Goblin", name, lifePoints);
        this.setLife(6+lifePoints);
        this.setAttackLevel(1);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;
    }
}