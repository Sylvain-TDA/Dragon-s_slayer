package fr.dragonsslayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class handle the game in itself. I will start the game, initialize the board, display ennemies, rolling the dice...
 */

public class Game {
    private int playerPosition;
    private final ArrayList<String> board = new ArrayList<>();
    private final int[] ennemiesPosition = new int[5];
    private final int[] chestPosition = new int[5];
    Menu menu = new Menu();
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
     * Then it rolls the dice and move the player.
     */

    public void startingAGame() throws InterruptedException {
        playerPosition = 0;
        setBoard();
        String intro = voidText + "\n" +
                "Votre aventure débute..." + "\n" +
                voidText + "\n" +
                "Vous êtes ensevelis sous un monticule de formes desquelles suintent un liquide étrange." + "\n" +
                "Après avoir réveillé vos yeux, ce liquide semble être le sang qui s'écoule des cadavres qui vous entourent." + "\n" +
                "Prenant votre courage à deux mains, vous dégagez celle d'un héro précédent pour vous mettre sur vos deux jambes, bien entières." + "\n" +
                voidText + "\n" +
                "Votre position est : " + "\n" +
                playerPosition;
        menu.toString(intro);
        Thread.sleep(700);
    }

    public int playingTheGame() throws InterruptedException, HeroOutOfTheBoardException {
        while(playerPosition != 5) {
            int diceValue = 1;
            playerPosition += diceValue;
            menu.toString(voidText + "Vous lancez le dé. Et vous faites : " + diceValue);
            String movingForward = "Vous avancez en case : " + playerPosition + voidText;
            menu.toString(movingForward);

            if (playerPosition > 5) {
                throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");
            } else if (playerPosition == 5) {
                String winGame = "Bravo, vous avez gagné !";
                menu.toString(winGame);
                return playerPosition;
            }

        }
        /*
        while (true) {
               Thread.sleep(700);
               int diceValue = dice();
               menu.toString(voidText + "Vous lancez le dé. Et vous faites : " + diceValue);
               Thread.sleep(700);

               playerPosition += diceValue;

               if (playerPosition > 64) {
                   throw new HeroOutOfTheBoardException("Oups, vous êtes au-delà des méandres du vide !");
               } else if (playerPosition == 64) {
                   String winGame = "Bravo, vous avez gagné !";
                   menu.toString(winGame);
                   return playerPosition;
               }

               String movingForward = "Vous avancez en case : " + playerPosition + voidText;
               menu.toString(movingForward);
           }

        */
        return playerPosition;
    }


    public int getPlayerPosition() {
        return playerPosition;
    }

    public void getBoard() {
        System.out.println(board);
    }

    public void getEnnemyPosition() {
        System.out.println(board.indexOf("Ennemy"));
    }

    public void getChestPosition() {
        System.out.println(board.indexOf("Chest"));
    }

    public void setBoard() {
        board.add("Empty");
        board.add("Ennemy");
        board.add("Weapon");
        board.add("Potion");
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
