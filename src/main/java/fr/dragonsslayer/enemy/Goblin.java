package fr.dragonsslayer.enemy;

public class Goblin extends Ennemy {
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