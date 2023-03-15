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
                gameSpeed();
                //ga.drawBackground();
            }
            try {
                ga.moveBlockDown();
                Thread.sleep(speed);
            } catch (InterruptedException e) {
            }
        }
    }
    void gameSpeed(){

        if(ga.score>0){
            System.out.println("speed 800");
            speed=800;
        }
        if(ga.score>1){
            System.out.println("speed 600");
            speed=500;
        }
        if(ga.score>2){
            System.out.println("speed 400");
            speed=300;
        }
        if(ga.score>3){
            System.out.println("speed 200");
            speed=50;
        }
    }
}