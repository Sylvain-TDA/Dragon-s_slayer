package fr.dragonsslayer.board;

import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.ennemy.Sorcerer;
import fr.dragonsslayer.equipment.Potion;

public class EnnemyCell extends Cell{
    public EnnemyCell(Hero ennemy) {
        super(ennemy);
    }

    @Override
    public void interact() {
        Hero ennemy = (Hero) getContent(); // Cast du contenu en Sorcerer
        System.out.println("Vous vous retrouvez face à un ennemi : ");
        System.out.println(ennemy.toString()); // Appel de getAttribute()
    }

    @Override
    public String getType() {
        return "Ennemy";
    }
}