package fr.dragonsslayer.enemy;

public class Dragon extends Ennemy {
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
