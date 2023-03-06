import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameThread extends Thread {
    private GameArea ga;

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
                System.out.println("nowy spawn");
                ga.spawnBlock();
                //ga.drawBackground();
            }
            try {
                ga.moveBlockDown();
                Thread.sleep(800);
            } catch (InterruptedException e) {
            }
        }
    }
}