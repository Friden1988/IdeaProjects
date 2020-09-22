package life;

public class GameController {
    private int cyclesCount;
    private final GameModel gameModel;

    public GameController(int cyclesCount, GameModel gameModel) {
        this.cyclesCount = cyclesCount;
        this.gameModel = gameModel;
    }

    public void run() throws InterruptedException {
        while (cyclesCount != 0) {
            Thread.sleep(100L);
            gameModel.createGeneration();
            gameModel.updateGeneration(cyclesCount);
            cyclesCount--;
        }
    }
}