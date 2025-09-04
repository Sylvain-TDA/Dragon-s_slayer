package fr.dragonsslayer.enemy;

public class Bogle extends Ennemy{
    public Bogle(String name) {
        super("Mauvais esprit", name);
        this.setLife(15);
        this.setAttackLevel(4);
    }


    @Override
    public String getType() {
        return "Bogle";
    }
}
