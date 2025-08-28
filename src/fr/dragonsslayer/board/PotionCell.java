package fr.dragonsslayer.board;

import fr.dragonsslayer.equipment.Potion;

public class PotionCell extends Cell{
    public PotionCell(Potion potion) {
        super(potion);
    }

    @Override
    public void interact() {
        System.out.println("Vous avez trouvé une potion : " + ((Potion) getContent()));
    }
}
