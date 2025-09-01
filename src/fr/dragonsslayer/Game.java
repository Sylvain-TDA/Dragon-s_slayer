package fr.dragonsslayer;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Random;

import fr.dragonsslayer.board.*;
import fr.dragonsslayer.ennemy.Sorcerer;
import fr.dragonsslayer.equipment.Potion;
import fr.dragonsslayer.equipment.Weapon;

/**
 * This class handle the game in itself. I will start the game, initialize the board, display ennemies, rolling the dice...
 */

public class Game {
    private int playerPosition;
    private final ArrayList<Cell> board = new ArrayList<>();
    private final int[] ennemiesPosition = new int[5];
    private final int[] chestPosition = new int[5];
    public String voidText = "                          ";


    /**
     * Initialize the dice and "roll it" by randomizing a number between 1 and 6.
     *
     * @return the result of the dice that have been rolled.
     */

    public int dice() {
        return new Random().nextInt(6) + 1;
    }

    /**
     * Start the game with a text introduction and the player's position at 0.
     * It also sets the board as it is necessary at the beginning of a game.
     *
     * @throws InterruptedException if the thread is interrupted during the game.
     */

    public void startingAGame() throws InterruptedException {
        playerPosition = 0;
        initBoard();
        String intro = voidText + "\n" +
                "Votre aventure débute..." + "\n" +
                voidText + "\n" +
                "Vous êtes ensevelis sous un monticule de formes desquelles suintent un liquide étrange." + "\n" +
                "Après avoir réveillé vos yeux, ce liquide semble être le sang qui s'écoule des cadavres qui vous entourent." + "\n" +
                "Prenant votre courage à deux mains, vous dégagez celle d'un héro précédent pour vous mettre sur vos deux jambes, bien entières." + "\n" +
                voidText + "\n" +
                "Votre position est : " + "\n" +
                playerPosition;
        System.out.println(intro);
        Thread.sleep(700);
    }

    /**
     * Handle the game after is as begun.
     * It rolls the dice, move the player and handle the encounter on specific cells.
     *
     * @throws HeroOutOfTheBoardException if the hero goes out of the board.
     */

    public int playingTheGame() throws HeroOutOfTheBoardException {
        while (playerPosition != 5) {
            int diceValue = 1;
            playerPosition += diceValue;
            System.out.println(voidText + "Vous lancez le dé. Et vous faites : " + diceValue);
            String movingForward = "Vous avancez en case : " + playerPosition + voidText;
            System.out.println(movingForward);

            if (playerPosition > 4) {
                throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");
            } else if (playerPosition == 4) {
                System.out.println("Bravo, vous avez gagné !");
                return playerPosition;
            }
            Cell currentCell = board.get(playerPosition);
            currentCell.interact();
        }
        return playerPosition;
    }
        /*
        while (true) {
               Thread.sleep(700);
               int diceValue = dice();
               System.out.println(voidText + "Vous lancez le dé. Et vous faites : " + diceValue);
               Thread.sleep(700);

               playerPosition += diceValue;

               if (playerPosition > 64) {
                   throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");
               } else if (playerPosition == 64) {
                   String winGame = "Bravo, vous avez gagné !";
                   System.out.println(winGame);
                   return playerPosition;
               }

               String movingForward = "Vous avancez en case : " + playerPosition + voidText;
               System.out.println(movingForward);
           }

        */

    public int getPlayerPosition() {
        return playerPosition;
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public void getEnnemyPosition() {
        System.out.println(board.indexOf("Ennemy"));
    }

    public void getChestPosition() {
        System.out.println(board.indexOf("Chest"));
    }

    /**
     * Handle the initialization of the board.
     */

    public void initBoard() {
        board.add(new EmptyCell(new Empty()));
        board.add(new EmptyCell(new Empty()));
        board.add(new EnnemyCell(new Sorcerer("Gildur")));
        board.add(new WeaponCell(new Weapon("Huld", "spell", 2)));
        board.add(new PotionCell(new Potion("Potion de soin", "Soin", 2)));
    }

    public void showBoard(){
        System.out.println(board);
    }
    /*
    public void setEnnemyPosition() {
        Random random = new Random();
        int count = 0;

        while (count < 10) {
            int pos = random.nextInt(board.length);
            if (board[pos].equals("empty")) {
                board[pos] = "enemy";
                count++;
            }
        }
    }



    public void setChestPosition() {
        Random random = new Random();
        int count = 0;

        while (count < 15) {
            int pos = random.nextInt(board.length);
            if (board[pos] == null) {
                board[pos] = "chest";
                count++;
            }
        }
    }
    */
}
