
import javax.swing.*;

public class MainGamePanel extends JPanel {
    private GameArea ga;


    MainGamePanel() {
        //this.setPreferredSize(new Dimension(500,500));
      //  this.setBackground(Color.pink);
        ga = new GameArea(10);
        this.add(ga);
        startGame();
    }

    public void startGame(){
        new GameThread(ga).start();
    }


}
