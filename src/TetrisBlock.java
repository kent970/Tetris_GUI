import java.awt.*;
import java.util.Random;

public class TetrisBlock {
    Random random = new Random();

    private int[][] shape;
    private Color color;
    private int x, y;
    private int xBound;
    private int shapes[][][];
    private int currentRotation;


    TetrisBlock(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.x = 5;
        this.y = 0;
        initShapes();
    }

    private void initShapes() {
        shapes = new int[4][][];
        for (int i = 0; i < shapes.length; i++) {
            int row = shape[0].length;
            int column = shape.length;

            shapes[i] = new int[row][column];
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    shapes[i][j][k] = shape[column - 1 - k][j];
                }
            }
            shape = shapes[i];
        }
    }

    public void spawn(int gridWidth) {
        currentRotation = 0;
        shape = shapes[currentRotation];
        y = -getHeight();
        x = (gridWidth - getWidth()) / 2;
        xBound = gridWidth;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void rotateBlock() {
        currentRotation++;
        if (currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];

    }

    public int getBottomEdge() {
        return y + getHeight();
    }

    public int getLeftEdge() {
        return x;
    }

    public int getRightEdge() {
        return x + getWidth();
    }
}

