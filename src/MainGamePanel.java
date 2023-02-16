import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import java.util.Random;
import java.util.Random.*;
import javax.swing.*;

public class MainGamePanel extends JPanel {


    MainGamePanel() {
        //this.setPreferredSize(new Dimension(500,500));
      //  this.setBackground(Color.pink);
        this.add(new GameArea(10));

    }


}
