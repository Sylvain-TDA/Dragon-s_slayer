package fr.dragonsslayer.enemy;

public class Dragon extends Ennemy {
    public Dragon(String name, int lifePoints) {
        super("Dragon", name, lifePoints);
        this.setLife(15+lifePoints);
        this.setAttackLevel(4);
    }

    public String getAttribute() {
        return "nom : '" + this.getName() + '\'' + "\n" +
                "points de vie : " + this.getLife() + "\n" +
                "niveau d'attaque : " + this.getAttackLevel() + "\n" ;

    }
}
