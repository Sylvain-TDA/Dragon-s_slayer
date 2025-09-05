package fr.dragonsslayer.enemy;

public class Orc extends Ennemy{
    public Orc(String name, int lifePoints) {
        super("Orc", name, lifePoints);
        this.setLife(10+lifePoints);
        this.setAttackLevel(6);
    }

    @Override
    public String getType() {
        return "Orc";
    }
}
