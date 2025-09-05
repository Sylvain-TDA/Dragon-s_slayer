package fr.dragonsslayer.enemy;

public class Bogle extends Ennemy{
    public Bogle(String name, int lifePoints) {
        super("Mauvais esprit", name, lifePoints);
        this.setLife(15+lifePoints);
        this.setAttackLevel(4);
    }


    @Override
    public String getType() {
        return "Bogle";
    }
}
