package fr.dragonsslayer.board;

import fr.dragonsslayer.enemy.Dragon;
import fr.dragonsslayer.enemy.Sorcerer;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final ArrayList<Cell> cells;

    public Board() {
        this.cells = new ArrayList<>();
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i <= 64; i++) {
            cells.add(new EmptyCell(new Empty()));
        }
        // Placement des entités
        placeRandomly(new EnemyCell(new Dragon("Hield")), 4);
        placeRandomly(new EnemyCell(new Sorcerer("Hield")), 10);
        // ... autres placements
    }

    public void placeRandomly(Cell newCell, int count) {
        Random rand = new Random();
        int size = cells.size();
        for (int i = 0; i < count; i++) {
            int pos;
            do {
                pos = rand.nextInt(size);
            } while (!(cells.get(pos) instanceof EmptyCell));
            cells.set(pos, newCell);
        }
    }

    public Cell getCell(int position) {
        return cells.get(position);
    }

    public void showBoard() {
        for (Cell cell : cells) {
            System.out.println(cell);
        }
    }
}
