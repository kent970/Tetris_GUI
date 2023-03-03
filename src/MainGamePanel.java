
import javax.swing.*;

public class MainGamePanel extends JPanel {
    private GameArea ga;

    private JLabel jLabel;
    MainGamePanel() {
        //this.setPreferredSize(new Dimension(500,500));
        //  this.setBackground(Color.pink);
        ga = new GameArea(12, this);
        this.add(ga);
        jLabel = new JLabel("SCORE: " + ga.score);
        this.add(jLabel);
        startGame();
    }

    void setScore(int score) {
        jLabel.setText("SCORE: " + score);
    }

    public void startGame() {
        GameThread gameThread = new GameThread(ga);

        gameThread.start();
    }
}

