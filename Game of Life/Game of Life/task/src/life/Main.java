package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final var scanner = new Scanner(System.in);

        final int size = scanner.nextInt();
        scanner.close();

        final var gameView = new GameOfLife();
        final var gameModel = new GameModel(gameView, size);

        final int cyclesCount = 1000;

        new GameController(cyclesCount, gameModel).run();
    }
}