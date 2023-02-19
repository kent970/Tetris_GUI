import java.awt.*;
import java.util.Random;

public class TetrisBlock {
Random random = new Random();

    private int[][] shape;
    private Color color;
    private int x,y;
    private int xBound;

    TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.x = 5;
        this.y = 0;
    }

    public void spawn(int gridWidth){
        y=-getHeight();
        x= (gridWidth-getWidth())/2;
        xBound=gridWidth;
    }

    public Color getColorr() {
        return color;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getHeight() {
        return shape.length;
    }

    public int[][] getShape() {
        return shape;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void moveDown(){y++;}
    public void moveUp(){y--;}
    public void moveLeft(){
        if(x>0)
        x--;
    }
    public void moveRight(){
        if(x<(xBound-shape[0].length))
        x++;
    }
    public int getBottomEdge(){
        return y + getHeight();
    }


}
