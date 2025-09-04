package fr.dragonsslayer.Game;

import fr.dragonsslayer.Game.PrayerHandler;
import fr.dragonsslayer.Game.UserInputHandler;
import fr.dragonsslayer.Hero.Hero;
import fr.dragonsslayer.Hero.HeroManager;
import fr.dragonsslayer.board.*;
import fr.dragonsslayer.board.dice.Dice;
import fr.dragonsslayer.board.dice.SixFacesDice;
import fr.dragonsslayer.db.DataBaseHandling;
import fr.dragonsslayer.enemy.*;
import fr.dragonsslayer.equipment.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int playerPosition;
    private final ArrayList<Cell> board;
    private final UserInputHandler inputHandler;
    private final PrayerHandler prayerHandler;
    private final Dice dice;
    private Hero hero;
    public boolean endTheGame = false;

    public Game() {
        board = new ArrayList<>();
        this.inputHandler = new UserInputHandler();
        this.prayerHandler = new PrayerHandler(new DataBaseHandling());
        this.dice = new SixFacesDice();
    }

    /**
     * Init the board, initialize the game and start it.
     *
     * @throws InterruptedException if the Hero is after the 64th cell.
     */
    public void start() throws InterruptedException {
        initBoard();
        if (hero == null) {
            throw new IllegalStateException("Le héros n'a pas été défini. Utilisez setHero() avant de démarrer le jeu.");
        }
        startingAGame();
        try {
            playingTheGame();
        } catch (HeroOutOfTheBoardException e) {
            e.printStackTrace();
        }
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * Display the intro and initialize the hero position
     *
     * @throws InterruptedException to handle the thread sleep.
     */
    protected void startingAGame() throws InterruptedException {
        playerPosition = 0;
        String intro = """
                Votre aventure débute...
                Vous êtes ensevelis sous un monticule de formes desquelles suintent un liquide étrange.
                Après avoir réveillé vos yeux, ce liquide semble être le sang qui s'écoule des cadavres qui vous entourent.
                Prenant votre courage à deux mains, vous dégagez celle d'un héro précédent pour vous mettre sur vos deux jambes, bien entières.
                Votre position est : %d
                """.formatted(playerPosition);
        inputHandler.displayMessage(intro);
        Thread.sleep(700);
    }

    /**
     * Handle the player position and if the game as ended.
     * @throws HeroOutOfTheBoardException to handle the thread sleep.
     * @throws InterruptedException to handle the thread sleep.
     */
    protected void playingTheGame() throws HeroOutOfTheBoardException, InterruptedException {
        while (playerPosition < 64 && !endTheGame) {
            int diceValue = dice.roll();
            playerPosition += diceValue;
            inputHandler.displayMessage("Vous lancez le dé. Et vous faites : " + diceValue);
            Thread.sleep(700);
            inputHandler.displayMessage("Vous avancez en case : " + playerPosition);
            if (playerPosition >= 64) {
                playerPosition = 64;
                inputHandler.displayMessage("Bravo, vous avez gagné");
                return;
            }
            Cell currentCell = board.get(playerPosition);
            Thread.sleep(700);
            currentCell.interact(hero, this);
        }
    }

    public void prayForRespect() {
        prayerHandler.prayForRespect(hero);
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void updatedPlayerPosition(int randomNumber) {
        this.playerPosition = Math.max(0, playerPosition - randomNumber);
    }

    /**
     * Handle the initialization of the board.
     */
    public void initBoard() {

        for (int i = 0; i <= 64; i++) {
            board.add(new EmptyCell(new Empty()));
        }

        // Placement des entités
        placeRandomly(new EnemyCell(new Dragon("Hield")), 4);
        placeRandomly(new EnemyCell(new Sorcerer("Hield")), 10);
        placeRandomly(new EnemyCell(new Goblin("Hield")), 10);

        placeRandomly(new WeaponCell(new Club("Hield")), 5);
        placeRandomly(new WeaponCell(new Sword("Hield")), 4);
        placeRandomly(new WeaponCell(new Lightning("Hield")), 5);
        placeRandomly(new WeaponCell(new FireBall("Hield")), 2);

        placeRandomly(new PotionCell(new Potion("Potion de soin", 2)), 6);
        placeRandomly(new LargePotionCell(new LargePotion("Grande potion de soin", "Potion", 5)), 2);
    }

    /**
     * Initialize a random position in the board to fill it
     *
     * @param newCell new Cell type
     * @param count   number of cell to be placed
     */
    private void placeRandomly(Cell newCell, int count) {
        Random rand = new Random();
        int size = board.size();

        for (int i = 0; i < count; i++) {
            int pos;
            do {
                pos = rand.nextInt(size);
            } while (!(board.get(pos) instanceof EmptyCell));
            board.set(pos, newCell);
        }
    }

    private void showBoard() {
        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i));
        }
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }


}
