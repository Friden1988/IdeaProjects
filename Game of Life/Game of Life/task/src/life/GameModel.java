package life;

import java.util.Arrays;
import java.util.Random;

public class GameModel {
    private final GameOfLife gameView;
    private final Cell[][] cells;
    private final int size;
    private final Random random = new Random();

    public GameModel(GameOfLife gameView, int size) {
        this.size = size;
        this.gameView = gameView;
        cells = new Cell[size][size];

        gameView.fillCells(size);
        gameView.showGameWindow();
    }

    public void createGeneration() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == null) {
                    cells[i][j] = new Cell(random.nextBoolean());
                    continue;
                }
                var cell = cells[i][j];
                cell.setAliveNextGen(willSurviveCurrentGen(getNeighbours(i, j), cell.isAliveCurrentGen()));
            }
        }
    }

    public void updateGeneration(int cyclesCount) {
        int aliveCount = 0;

        gameView.setCurrentCycle(cyclesCount);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var cell = cells[i][j];
                boolean aliveStatus = cell.isAliveNextGen();

                cell.setAliveCurrentGen(aliveStatus);
                if (cell.isAliveCurrentGen()) {
                    aliveCount++;
                }
                gameView.changeCellAliveStatus(i, j, aliveStatus, aliveCount);

                cell.setAliveNextGen(false);
            }
        }
    }

    private boolean willSurviveCurrentGen(Cell[] neighbours, boolean isAliveCurrentGen) {
        long aliveNeighborsCount = Arrays.stream(neighbours).filter(Cell::isAliveCurrentGen).count();

        if (isAliveCurrentGen) {
            return aliveNeighborsCount == 2 || aliveNeighborsCount == 3;
        }
        return aliveNeighborsCount == 3;
    }

    public Cell[] getNeighbours(int x, int y) {
        return new Cell[]{cells[sub(x)][sub(y)], cells[sub(x)][y], cells[sub(x)][add(y)],
                cells[x][sub(y)], cells[x][add(y)],
                cells[add(x)][sub(y)], cells[add(x)][y], cells[add(x)][add(y)]};
    }

    private int sub(int position) {
        return position > 0 ? position - 1 : size - 1;
    }

    private int add(int position) {
        return position + 1 == size ? 0 : position + 1;
    }
}