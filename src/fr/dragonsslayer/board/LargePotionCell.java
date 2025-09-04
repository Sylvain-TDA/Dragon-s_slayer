package fr.dragonsslayer.board;

import fr.dragonsslayer.Game.Game;
import fr.dragonsslayer.Hero.Hero;
import fr.dragonsslayer.equipment.LargePotion;

public class LargePotionCell extends Cell {
    public LargePotionCell(LargePotion potion) {
        super(potion);
    }

    @Override
    public void interact(Hero hero, Game game) {
        LargePotion potion = (LargePotion) getContent();
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
