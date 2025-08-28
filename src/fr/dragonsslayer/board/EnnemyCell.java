package fr.dragonsslayer.board;

import fr.dragonsslayer.ennemy.Sorcerer;
import fr.dragonsslayer.equipment.Potion;

public class EnnemyCell extends Cell{
    public EnnemyCell(Sorcerer ennemy) {
        super(ennemy);
    }

    @Override
    public void interact() {
        Sorcerer ennemy = (Sorcerer) getContent(); // Cast du contenu en Sorcerer
        System.out.println("Vous vous retrouvez face à un ennemi : ");
        System.out.println(ennemy.getAttribute()); // Appel de getAttribute()
    }
}