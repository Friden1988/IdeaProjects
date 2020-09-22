package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    private JPanel[][] cells;

    private final JPanel textPanel;
    private final JPanel boardPanel;
    private final JPanel playPanel;
    private final JPanel resetPanel;

    private final JLabel generationLabel;
    private final JLabel aliveLabel;

    private final JToggleButton playButton;
    private final JButton resetButton;

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        setLocationRelativeTo(null);

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");

        generationLabel.setText("Generation #" + 0);
        aliveLabel.setText("Alive: " + 0);

        playButton = new JToggleButton();
        playButton.setName("PlayToggleButton");

        resetButton = new JButton();
        resetButton.setName("ResetButton");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        textPanel = new JPanel();
        playPanel = new JPanel();
        boardPanel = new JPanel();
        resetPanel = new JPanel();

        resetPanel.add(resetButton);
        playPanel.add(playButton);
        textPanel.add(generationLabel);
        textPanel.add(aliveLabel);



        add(textPanel);
        add(boardPanel);
        add(playPanel);
        add(resetPanel);
    }

    public void showGameWindow() {
        setVisible(true);
    }

    public void fillCells(int size) {
        boardPanel.setLayout(new GridLayout(size, size, 1, 2));

        cells = new JPanel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var cell = new JPanel();

                cells[i][j] = cell;
                cell.setBackground(Color.GRAY);
                boardPanel.add(cell);
            }
        }
    }

    public void setCurrentCycle(int cyclesCount) {
        generationLabel.setText("Generation #" + cyclesCount);
    }

    public void changeCellAliveStatus(int x, int y, boolean isAlive, int aliveCount) {
        cells[x][y].setBackground(isAlive ? Color.BLACK : Color.GRAY);
        aliveLabel.setText("Alive: " + aliveCount);
    }
}