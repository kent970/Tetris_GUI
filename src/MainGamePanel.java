
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainGamePanel extends JPanel {
    private GameArea ga;
    String data[][] = new String[10][3];
    String column[] = {"Nr","Wynik", "Gracz"};
    private JLabel jLabel;

    MainGamePanel() {
        //this.setPreferredSize(new Dimension(500,500));
        //  this.setBackground(Color.pink);
        ga = new GameArea(12, this);
        this.add(ga);
        jLabel = new JLabel("SCORE: " + ga.score);
        this.setLayout(new FlowLayout());
        this.add(jLabel);

        JTable jTable = new JTable(data, column){
          private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setBounds(30, 40, 230, 100);
        JScrollPane scrollPane = new JScrollPane(jTable);
        this.add(scrollPane);
        scrollPane.setEnabled(false);
        scrollPane.setFocusable(false);
        jTable.setFocusable(false);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);

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

    void readPersonScore() {
        try {
            FileInputStream fileInputStream = new FileInputStream("score_label.txt");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object[][] readTab = (Object[][]) objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();

            String[] stringTab = new String[readTab.length];
            int[] intTab = new int[readTab.length];
            for (int i = 0; i < readTab.length; i++) {
                stringTab[i] = String.valueOf(readTab[i][0]);
                intTab[i] = Integer.valueOf(String.valueOf(readTab[i][1]));
            }


            for (int i = 0; i < readTab.length; i++) {
                for (int j = i + 1; j < readTab.length; j++) {
                    // int lowerVal= (int) readTab[i][1];
                    // int higherVal= (int) readTab[i+1][1];
//  dodanie w scoreLabel nowego wyniku do tabeli i usuniecie namniejszego

                    if (intTab[i] > intTab[j]) {
                        // System.out.println("zamiana");
                        int tempInt = intTab[i];
                        String tempString = stringTab[i];
                        stringTab[i] = stringTab[j];
                        intTab[i] = intTab[j];

                        stringTab[j] = tempString;
                        intTab[j] = tempInt;
                        //tempTabInt;
                        //  readTab[i+1][1]= new Object[]{ tempTab};

                    }
                }
            }
            for (int i = 0; i < readTab.length; i++) {
                System.out.println(stringTab[i] + " " + intTab[i]);
            }

            for (int i = 0; i < readTab.length; i++) {
                data[i][0]=String.valueOf(i+1);
                data[i][1]=String.valueOf(intTab[i]);
                data[i][2]=stringTab[i];

            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}



