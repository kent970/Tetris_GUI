import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameArea extends JPanel {
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    GameArea(int columns){
        this.setVisible(true);
this.setPreferredSize(new Dimension(300,300));
    //this.setBackground(Color.pink);
    this.setBorder(new BevelBorder(1));
    gridColumns=columns;
    gridCellSize=300/ gridColumns;
    gridRows=300/gridCellSize;
    spawnBlock();
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      //  g.fillRect(0,0,200  ,200);
        for(int y=0;y<gridRows;y++) {
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(gridCellSize * x, gridCellSize*y, gridCellSize, gridCellSize);

            }
        }
        drawBlock(g);

    }
    private TetrisBlock block;

    public void spawnBlock(){
        Random random = new Random();
        int block1[][]={ {0,1},{1,1},{1,1}   };
        int block2[][]={ {1,1,1,1},{0,0,0,1}   };
        int block3[][]={ {1,1},{1,1},{0,1},{0,1}   };
        int block4[][]={ {1,1},{1,1}   };
        int blockTab[][][]={block1,block2,block3};
        block = new TetrisBlock( blockTab[random.nextInt(blockTab.length)], Color.BLACK);
        block.spawn(gridColumns);
    }

    public void moveBlockDown(){
        if(checkBottom()==false) return;
        block.moveDown();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            block.moveRight();
            repaint();
                System.out.println("rightt");
            }if(e.getKeyCode()==KeyEvent.VK_LEFT){
                block.moveLeft();
                repaint();
                System.out.println("lefttt");
            }


        }

    }
    private boolean checkBottom(){
        if(block.getBottomEdge()==gridRows){
            return false;
        }
        return true;
    }
    protected void drawBlock(Graphics g){
    for(int i=0;i< block.getHeight();i++){
        for(int j=0;j<block.getWidth();j++){
            if(block.getShape()[i][j]==1){
                g.setColor(block.getColorr());
                g.fillRect((j+block.getX())*gridCellSize,(i+ block.getY())*gridCellSize,gridCellSize,gridCellSize);
            }

        }


    }

    }
}
