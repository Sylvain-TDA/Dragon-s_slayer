package fr.dragonsslayer.board;

import fr.dragonsslayer.Game;
import fr.dragonsslayer.board.dice.Dice;
import fr.dragonsslayer.board.dice.TwentyFacesDice;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.Menu;

import java.util.Random;

public class EnemyCell extends Cell {
    public EnemyCell(Hero enemy) {
        super(enemy);
    }

    @Override
    public void interact(Hero hero, Game game) {
        Hero enemy = (Hero) getContent();
        System.out.println("Vous vous retrouvez face à un ennemi : ");
        System.out.println(enemy.toString());
        Menu menu = new Menu();
        Dice twentyFacesDice = new TwentyFacesDice();

        int enemyAttack = enemy.getAttackLevel();
        int enemyLife = enemy.getLife();
        int heroLife = hero.getLife();

        switch (menu.displayFightMenu()) {
            case 1:
                while (enemyLife > 0 && heroLife > 0) {
                    int heroAttack = hero.getAttackLevel();
                    int criticDice = twentyFacesDice.roll();
                    if (criticDice == 20) {
                        heroAttack += 2;
                        System.out.println("Vous faites un coup critique.");
                    } else if(criticDice == 0) {
                        heroAttack = 0;
                        System.out.println("Vous faites un échec critique.");
                    }
                    enemyLife -= heroAttack;
                    System.out.println("Vous attaquez l'ennemi");
                    if (enemyLife <= 0) {
                        System.out.println("L'ennemi succombe sous votre assaut");
                        break;
                    } else {
                        System.out.println("La vie de l'ennemi tombe : " + enemyLife);
                        heroLife -= enemyAttack;
                        System.out.println("L'ennemi vous a attaqué, votre vie est maintenant à : " + heroLife);
                        hero.setLife(heroLife);
                    }
                }
                break;
            case 2:
                game.updatedPlayerPosition(new Random().nextInt(6)+1);
                System.out.println("Vous reculez en case : " + game.getPlayerPosition());
                break;
        }
    }

    @Override
    public String getType() {
        return "enemy";
    }
}