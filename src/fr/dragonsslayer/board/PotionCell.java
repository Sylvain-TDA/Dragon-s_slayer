package fr.dragonsslayer.board;

import fr.dragonsslayer.Game;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.Potion;

public class PotionCell extends Cell {
    public PotionCell(Potion potion) {
        super(potion);
    }

    @Override
    public void interact(Hero hero, Game game) {
        Potion potion = (Potion) getContent();
        System.out.println("Vous avez trouvé une potion : " + potion);

        int newLifePoints = potion.heal(hero, potion.getHealAmount());
        hero.setLife(newLifePoints);

        System.out.println("""
                Votre vie est maintenant à :\s""" + newLifePoints + """
                
                """);
    }

    @Override
    public String getType() {
        return "Potion";
    }
}
