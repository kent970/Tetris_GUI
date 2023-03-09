import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameArea extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] backgroundTab;
    private boolean bottomList[][];
    public boolean isGameOver = false;
    public int score = 0;

    private MainGamePanel mainGamePanel;

    GameArea(int columns, MainGamePanel mainGamePanel) {
        this.mainGamePanel = mainGamePanel;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(300, 300));
        //this.setBackground(Color.pink);
        this.setBorder(new BevelBorder(1));
        gridColumns = columns;
        gridCellSize = 300 / gridColumns;
        gridRows = 300 / gridCellSize;
        backgroundTab = new Color[gridRows][gridColumns];
        spawnBlock();
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        bottomList = new boolean[gridRows][gridColumns];
        for (int y = 0; y < bottomList.length; y++) {
            for (int x = 0; x < bottomList[0].length; x++) {
                bottomList[y][x] = true;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //  g.fillRect(0,0,200  ,200);
        for (int y = 0; y < gridRows; y++) {
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(gridCellSize * x, gridCellSize * y, gridCellSize, gridCellSize);

            }
        }
        drawBackground(g);
        drawBlock(g);
    }

    private TetrisBlock block;

    public void spawnBlock() {
        checkRow();
        Random random = new Random();
        int block1[][] = {{1,1,1,1}};
        int block2[][] = {{1, 1, 1}, {1, 0, 0}};
        int block3[][] = {{1, 1, 1}, {0, 0 ,1}};
        int block4[][] = {{1, 1}, {1, 1}};
        int block5[][]={ {0,1,1},{1,1,0}   };
        int block6[][]={ {1,1,0},{0,1,1} };
        int block7[][]={{0,1,0},{1,1,1}  };
        int blockTab[][][] = {block1, block2, block3,block4,block5,block6,block7};
        Color colortab[] = {Color.BLACK, Color.BLUE, Color.ORANGE, Color.red, Color.green};
        block = new TetrisBlock(blockTab[random.nextInt(blockTab.length)], colortab[random.nextInt(colortab.length)]);
        block.spawn(gridColumns);
    }

    public void moveBlockDown() {
        if (checkBottom() == false) {
            return;
        }
        block.moveDown();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (checkRight() == true)) {
                block.moveRight();
                repaint();
            }
            if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (checkLeft() == true)) {
                block.moveLeft();
                repaint();
            }
            if ((e.getKeyCode() == KeyEvent.VK_UP) && (checkBottom() == true)) {
                block.rotateBlock();
                if(block.getRightEdge()>=gridColumns)  block.setX(gridColumns-block.getWidth());
                if(block.getLeftEdge()<0)   block.setY(0);
                System.out.println("rotation");
                repaint();
            }
            if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (checkBottom() == true)) {
                while (checkBottom() == true) {
                    block.moveDown();
                }
                repaint();
            }
        }
    }

    boolean checkBottom() {
        if ((block.getBottomEdge() == gridRows)) {
            {
                moveToBackground();
                return false;
            }
        }
        int[][] shape = block.getShape();

        for (int i = 0; i < block.getWidth(); i++) {
            for (int j = block.getHeight() - 1; j >= 0; j--) {
                if (shape[j][i] != 0) {
                    if ((j + block.getY()) < 0) break;
                    if (backgroundTab[j + block.getY() + 1][i + block.getX()] != null) {
                        moveToBackground();
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    boolean checkLeft() {
        if (block.getLeftEdge() == 0) {
            return false;
        }
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (col < shape.length && row < shape[0].length) {
                    if (shape[col][row] != 0) {
                        int x = col + block.getX() - 1;
                        int y = row + block.getY();

                        if (y < 0) break;
                        if (backgroundTab[y][x] != null) return false;
                        break;
                    }
                }
            }
        }
        return true;
    }

    boolean checkRight() {
        if (block.getRightEdge() == gridColumns) {
            System.out.println("check right");
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (backgroundTab[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }

    private void moveToBackground() {
        if (block.getY() <= 0) {
            isGameOver = true;
        }
        for (int y = 0; y < block.getShape().length; y++) {
            for (int x = 0; x < block.getShape()[0].length; x++) {
                if ((block.getY() >= 0) && block.getX() >= 0) {

                    if (block.getShape()[y][x] == 1) {
                        backgroundTab[y + block.getY()][x + block.getX()] = block.getColorr();
                    }
                }
            }
        }
    }

    private void checkRow() {
        for (int i = 0; i < gridColumns; i++) {
            int count = 0;

            for (int j = 0; j < gridRows; j++) {
                if (backgroundTab[i][j] != null) {
                    count++;
                }
            }
            if (count == gridRows) {
                clearRow(i);
            }
            // System.out.println("count checkrow: "+count);
        }
    }

    private void clearRow(int i) {
        for (int k = i; k > 0; k--) {
            for (int j = 0; j < backgroundTab[i].length; j++) {
                backgroundTab[k][j] = backgroundTab[k - 1][j];
            }
        }
        score++;
        mainGamePanel.setScore(score);
        System.out.println("SCORE : "+score);
    }

    protected void drawBlock(Graphics g) {
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if (block.getShape()[i][j] == 1) {
                    g.setColor(block.getColorr());
                    g.fillRect((j + block.getX()) * gridCellSize, (i + block.getY()) * gridCellSize, gridCellSize, gridCellSize);
                    g.setColor(Color.black);
                    g.drawRect((j + block.getX()) * gridCellSize, (i + block.getY()) * gridCellSize, gridCellSize, gridCellSize);
                }
            }
        }
    }

    private void drawBackground(Graphics g) {
        Color color;
        for (int y = 0; y < gridRows; y++) {
            for (int x = 0; x < gridColumns; x++) {
                color = backgroundTab[y][x];
                if (color != null) {
                    g.setColor(color);
                    g.fillRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
                }
            }
        }
    }
    public void gameOver(){
        repaint();
        System.out.println("GAME OVER");
        this.setFocusable(false);
        scoreLabel scoreLabel = new scoreLabel(score);
        JFrame goFrame = new JFrame();
        goFrame.setTitle("GAME OVER");
        goFrame.setBounds(100, 50, 200, 200);
        goFrame.setResizable(true);
        goFrame.setDefaultCloseOperation(3);
        goFrame.setContentPane(scoreLabel);
        goFrame.setVisible(true);

    }
}

