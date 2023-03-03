import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GameFrame() {
        this.setContentPane(new MainGamePanel());
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout(0));
        this.pack();
    }


}
