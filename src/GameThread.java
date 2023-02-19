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
            try {
                ga.moveBlockDown();
                Thread.sleep(400);
            } catch (InterruptedException e) {

            }

        }
    }



}
