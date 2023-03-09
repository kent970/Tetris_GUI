
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainGamePanel extends JPanel {
    private GameArea ga;
    String data[][]={{"12","gt"},{"hee","l33"}};
    String column[]={"34","leee"};
    private JLabel jLabel;

    MainGamePanel() {
        //this.setPreferredSize(new Dimension(500,500));
        //  this.setBackground(Color.pink);
        ga = new GameArea(12, this);
        this.add(ga);
        jLabel = new JLabel("SCORE: " + ga.score);
        this.setLayout(new FlowLayout());
        this.add(jLabel);

        this.add(new JLabel("sdsd"));

        JTable jTable = new JTable(data,column);
        jTable.setBounds(30,40,200,200);
        JScrollPane scrollPane = new JScrollPane(jTable);
        scrollPane.setEnabled(false);
        //this.add(scrollPane);
        readPersonScore();
        startGame();

    }

    void setScore(int score) {
        jLabel.setText("SCORE: " + score);
    }

    public void startGame() {
        GameThread gameThread = new GameThread(ga);

        gameThread.start();
    }
    void readPersonScore(){
        HashMap<String,Integer> readPersonScore = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("score_label.txt");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap hashMap = (HashMap)objectInputStream.readObject();


            fileInputStream.close();
            objectInputStream.close();

         //   for( Object i : hashMap.keySet()){
         //       System.out.println(i);
          //  }
            for (Object i : hashMap.keySet()) {
                System.out.print(i+" ");
            }
            System.out.println();
            for (Object i : hashMap.values()) {
                System.out.print(" "+i+" ");
            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}



