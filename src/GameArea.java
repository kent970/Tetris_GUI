import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class GameArea extends JPanel {
    int block[][]= { {1,0},{1,1},{1,1} }  ;
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
    protected void drawBlock(Graphics g){
    for(int i=0;i< block.length;i++){
        for(int j=0;j<block[0].length;j++){
            if(block[i][j]==1){
                g.fillRect(j*gridCellSize,i*gridCellSize,gridCellSize,gridCellSize);
            }

        }


    }

    }
}
