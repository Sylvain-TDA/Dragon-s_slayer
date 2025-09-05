package fr.dragonsslayer.board;

import fr.dragonsslayer.Game.Game;
import fr.dragonsslayer.Hero.HeroManager;
import fr.dragonsslayer.board.dice.Dice;
import fr.dragonsslayer.board.dice.SixFacesDice;
import fr.dragonsslayer.board.dice.TwentyFacesDice;
import fr.dragonsslayer.Hero.Hero;
import fr.dragonsslayer.Game.Menu;
import fr.dragonsslayer.db.DataBaseHandling;

import java.util.Objects;
import java.util.Random;

public class EnemyCell extends Cell {
    String choice;
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
        System.out.println("""
       \s
        Vous vous retrouvez face à un ennemi :\s
       \s""");
        System.out.println(enemy.toString());

        if (Objects.equals(hero.getType(), "Warrior") && Objects.equals(enemy.getType(), "Orc")) {
            initFight(game, hero);
        } else if (Objects.equals(hero.getType(), "Warrior") && Objects.equals(enemy.getType(), "Bogle")) {
            System.out.println("Le mauvais espirt fuit devant le manque cruel du votre.");
        } else if (Objects.equals(hero.getType(), "Magician") && Objects.equals(enemy.getType(), "Bogle")) {
            initFight(game, hero);
        } else if (Objects.equals(hero.getType(), "Magician") && Objects.equals(enemy.getType(), "Orc")) {
            System.out.println("L'orc est appeuré par vos pouvoirs");
        } else {
            initFight(game, hero);
        }
    }

    @Override
    public String getType() {
        return "enemy";
    }

    private void initFight(Game game, Hero hero) {
        DataBaseHandling db = new DataBaseHandling();
        Menu menu = new Menu(db, game, new HeroManager(db));
        Dice sixFacesDice = new SixFacesDice();

        switch (menu.displayFightMenu()) {
            case 1:
                choice = menu.displayInventoryMenu();
                fight(hero, game, choice);
                break;
            case 2:
                System.out.println("Vous tentez de fuir...");
                if (sixFacesDice.roll() == 1) {
                    game.updatedPlayerPosition(new
                            Random().nextInt(6) + 1);
                    System.out.println("Vous reculez en case : " + game.getPlayerPosition());
                    break;
                } else {
                    System.out.println("""
                            
                            Votre tentative a échouée...
                            
                            """);
                    fight(hero, game, choice);
                }
        }
    }

    private void fight(Hero hero, Game game, String choice) {
        Dice twentyFacesDice = new TwentyFacesDice();
        Hero enemy = (Hero) getContent();
        int enemyLife = enemy.getLife();
        int heroLife = hero.getLife();
        String enemyType = enemy.getType();
        int xpWon;
        System.out.println("""
                ====================
                """);

        while (enemyLife > 0 && heroLife > 0) {
            int heroAttack = hero.getAttackLevel();
            int enemyAttack = enemy.getAttackLevel();
            int criticDice = twentyFacesDice.roll();
            if (criticDice == 20) {
                heroAttack += 2;
                System.out.println("Vous faites un coup critique.");
            } else if (criticDice == 1) {
                heroAttack = 0;
                System.out.println("Vous faites un échec critique.");
            }

            if (Objects.equals(enemyType, "Dragon") && Objects.equals(choice, "Bow")) {
                heroAttack += 6;
            } else if (!Objects.equals(enemyType, "Dragon") && Objects.equals(choice, "Bow")) {
                heroAttack += 4;
            } else {
                if (Objects.equals(enemyType, "Bogle") && Objects.equals(choice, "Invisibility")) {
                    heroAttack += 8;
                } else if (!Objects.equals(enemyType, "Bogle") && Objects.equals(choice, "Invisibility")) {
                    heroAttack += 5;
                }
            }
            enemyLife -= heroAttack;
            System.out.println("Vous attaquez l'ennemi");
            if (enemyLife <= 0) {
                System.out.println("L'ennemi succombe sous votre assaut");
                System.out.println("""
                ====================
                """);
                if (Objects.equals(enemyType, "Dragon")) {
                    xpWon = 20;
                } else if (Objects.equals(enemyType, "Goblin")){
                    xpWon = 8;
                } else {
                    xpWon = 12;
                }
                hero.storeXp(xpWon);
                hero.setLevel(hero.getXp());
                break;
            } else {
                System.out.println("La vie de l'ennemi tombe à : " + enemyLife);
                int enemyCriticalDice = twentyFacesDice.roll();
                if (enemyCriticalDice == 20) {
                    enemyAttack += 2;
                    System.out.println("L'ennemi fait un coup critique.");
                } else if (enemyCriticalDice == 1) {
                    enemyAttack = 0;
                    System.out.println("L'enemi fait un échec critique.");
                }
                heroLife -= enemyAttack;
                if (heroLife <= 0) {
                    System.out.println("L'ennemi vous a attaqué et vous a fait perdre " + enemyAttack + " points de vie.");
                    System.out.println("Vous rejoingnez ceux que vous avez croisé lors de votre réveil.");
                    System.out.println("""
                --------------------
                """);
                } else {
                    System.out.println("L'ennemi vous a attaqué, votre vie est maintenant à : " + heroLife + ".");
                    if (heroLife <= 0) {
                        System.out.println("L'ennemi vous a attaqué et vous a fait perdre " + enemyAttack + " points de vie.");
                        System.out.println("Vous rejoignez ceux que vous avez croisés lors de votre réveil.");
                        game.endTheGame = true;
                        break;
                    }
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