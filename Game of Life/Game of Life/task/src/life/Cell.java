package life;

public class Cell {
    private boolean isAliveCurrentGen;
    private boolean isAliveNextGen;

    public Cell(boolean isAliveNextGen) {
        isAliveCurrentGen = false;
        this.isAliveNextGen = isAliveNextGen;
    }

    public boolean isAliveCurrentGen() {
        return isAliveCurrentGen;
    }

    public void setAliveCurrentGen(boolean aliveCurrentGeneration) {
        isAliveCurrentGen = aliveCurrentGeneration;
    }

    public boolean isAliveNextGen() {
        return isAliveNextGen;
    }

    public void setAliveNextGen(boolean aliveNextGeneration) {
        isAliveNextGen = aliveNextGeneration;
    }
}