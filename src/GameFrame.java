import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    MainGamePanel gamePanel = new MainGamePanel();

    GameFrame() {
        this.setContentPane(gamePanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout(0));
        this.pack();
    }

    private void setScore(int score){
        gamePanel.setScore(score);
    }



}
