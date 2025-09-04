package fr.dragonsslayer.board;

import fr.dragonsslayer.Game.Game;
import fr.dragonsslayer.Hero.HeroManager;
import fr.dragonsslayer.board.dice.Dice;
import fr.dragonsslayer.board.dice.TwentyFacesDice;
import fr.dragonsslayer.Hero.Hero;
import fr.dragonsslayer.Game.Menu;
import fr.dragonsslayer.db.DataBaseHandling;

import java.util.Objects;
import java.util.Random;

public class EnemyCell extends Cell {
    public EnemyCell(Hero enemy) {
        super(enemy);
    }

    /**
     * The cell interact and handle the battle.
     *
     * @param hero to acces the getter of the hero
     * @param game send the value of true to end the game
     */

    @Override
    public void interact(Hero hero, Game game) {
        Hero enemy = (Hero) getContent();
        System.out.println("Vous vous retrouvez face à un ennemi : ");
        System.out.println(enemy.toString());
        DataBaseHandling db = new DataBaseHandling();
        Menu menu = new Menu(db, game, new HeroManager(db));


        switch (menu.displayFightMenu()) {
            case 1:
                if (Objects.equals(hero.getType(), "Warrior") && Objects.equals(enemy.getType(), "Orc")) {
                    fight(hero, game);
                } else if (Objects.equals(hero.getType(), "Warrior") && Objects.equals(enemy.getType(), "Bogle")) {
                    System.out.println("Le mauvais espirt fuit devant le manque cruel du votre.");
                } else if (Objects.equals(hero.getType(), "Magician") && Objects.equals(enemy.getType(), "Bogle")) {
                    fight(hero, game);
                } else if (Objects.equals(hero.getType(), "Magician") && Objects.equals(enemy.getType(), "Orc")) {
                    System.out.println("L'orc est appeuré par vos pouvoirs");
                } else {
                    fight(hero, game);
                }
                break;
            case 2:
                game.updatedPlayerPosition(new
                        Random().nextInt(6) + 1);
                System.out.println("Vous reculez en case : " + game.getPlayerPosition());
                break;
        }
    }

    @Override
    public String getType() {
        return "enemy";
    }

    private void fight(Hero hero, Game game) {
        Dice twentyFacesDice = new TwentyFacesDice();
        Hero enemy = (Hero) getContent();
        int enemyAttack = enemy.getAttackLevel();
        int enemyLife = enemy.getLife();
        int heroLife = hero.getLife();
        while (enemyLife > 0 && heroLife > 0) {
            int heroAttack = hero.getAttackLevel();
            int criticDice = twentyFacesDice.roll();
            if (criticDice == 20) {
                heroAttack += 2;
                System.out.println("Vous faites un coup critique.");
            } else if (criticDice == 0) {
                heroAttack = 0;
                System.out.println("Vous faites un échec critique.");
            }
            enemyLife -= heroAttack;
            System.out.println("Vous attaquez l'ennemi");
            if (enemyLife <= 0) {
                System.out.println("L'ennemi succombe sous votre assaut");
                break;
            } else {
                System.out.println("La vie de l'ennemi tombe à : " + enemyLife);
                heroLife -= enemyAttack;
                if (heroLife <= 0) {
                    System.out.println("Vous rejoingnez ceux que vous avez croisé lors de votre réveil.");
                } else {
                    System.out.println("L'ennemi vous a attaqué, votre vie est maintenant à : " + heroLife);
                }
                hero.setLife(heroLife);
            }
            if (heroLife <= 0) {
                game.endTheGame = true;
                break;
            }
        }

    }
}