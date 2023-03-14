
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
        try {
            FileInputStream fileInputStream = new FileInputStream("score_label.txt");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            String[][] readTab = (String[][])objectInputStream.readObject();


            fileInputStream.close();
            objectInputStream.close();



            for(int i=0;i<readTab.length-1;i++){
                int lowerVal= Integer.parseInt(readTab[i][1]);
                int higherVal=Integer.parseInt(readTab[i+1][1]);
//  nie czyta sie int do stringa. po wczytaniu inta do stringa sortowanie tabeli, a potem dodanie w scoreLabel nowego wyniku do tabeli i usuniecie namniejszego

                if(lowerVal< higherVal){
                    System.out.println("zamiana");
                    String[] tempTab ={readTab[i][1]};
                    readTab[i][1]=readTab[i+1][1];
                  readTab[i+1][1]= Arrays.toString(tempTab);
               }

            }



            for(int i=0;i<readTab.length;i++){
                System.out.println();
                for(int j=0;j<readTab[i].length;j++){
                    System.out.print(readTab[i][j]);


                }

            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}



