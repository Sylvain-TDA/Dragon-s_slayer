package fr.dragonsslayer.enemy;

public class Orc extends Ennemy{
    public Orc(String name) {
        super("Orc", name);
        this.setLife(10);
        this.setAttackLevel(6);
    }

    @Override
    public String getType() {
        return "Orc";
    }
}
