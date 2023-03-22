import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameThread extends Thread {
    private GameArea ga;
    int speed = 1000;

    public GameThread(GameArea ga) {
        this.ga = ga;
    }

    public void run() {
        ga.moveBlockDown();
        while (true) {
            if (ga.isGameOver == true) {
                ga.gameOver();
                break;
            }
            if (ga.checkBottom() == false) {
               // System.out.println("nowy spawn");
                ga.spawnBlock();
               // gameSpeed();
                //ga.drawBackground();
            }
            try {
                ga.moveBlockDown();
                Thread.sleep(1000/(((ga.score/1000)+1)));
            } catch (InterruptedException e) {
            }
        }
    }
}