
import javax.swing.*;

public class MainGamePanel extends JPanel {
    private GameArea ga;

    MainGamePanel() {
        //this.setPreferredSize(new Dimension(500,500));
        //  this.setBackground(Color.pink);
        ga = new GameArea(10);
        this.add(ga);
        JLabel jLabel = new JLabel("SCORE: " + ga.score);
        this.add(jLabel);
        setScore(jLabel);
        startGame();
    }

    void setScore(JLabel jlabel) {
        jlabel.setText("SCORE: " + ga.score);
    }

    public void startGame() {
        GameThread gameThread = new GameThread(ga);

        gameThread.start();
    }
}

