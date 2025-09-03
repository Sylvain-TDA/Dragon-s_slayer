package fr.dragonsslayer.board;

import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.ennemy.Sorcerer;
import fr.dragonsslayer.equipment.Potion;

public class EnnemyCell extends Cell {
    public EnnemyCell(Hero ennemy) {
        super(ennemy);
    }

    @Override
    public void interact(Hero hero) {
        Hero ennemy = (Hero) getContent();
        System.out.println("Vous vous retrouvez face à un ennemi : ");
        System.out.println(ennemy.toString());
        boolean ennemyAttacked = false;
        while (ennemy.getLife() > 0 && !ennemyAttacked) {
            int ennemyAttack = ennemy.getAttackLevel();
            int ennemyLife = ennemy.getLife();
            int heroLife = hero.getLife();
            int heroAttack = hero.getAttackLevel();

            ennemyLife -= heroAttack;

            if (ennemyLife <= 0) {
                System.out.println("L'ennemi succombe sous votre assaut");
                ennemyAttacked = true;

            } else {
                System.out.println("Vous attaquez l'ennemi, sa vie tombe à : " + ennemyLife);
                heroLife -= ennemyAttack;
                ennemyAttacked = true;

                if (heroLife <= 0) {
                    System.out.println("Vous rejoignez le cimetière dans lequel vous êtes arrivé.");
                    hero.setLife(0);
                } else {
                    System.out.println("L'ennemi vous a attaqué, votre vie est maintenant à : " + heroLife);
                    System.out.println("Il prend la fuite...");
                    hero.setLife(heroLife);
                }
            }
        }
    }

    @Override
    public String getType() {
        return "Ennemy";
    }
}