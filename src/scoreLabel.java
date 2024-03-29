import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class scoreLabel extends JPanel {

    //HashMap<String,Integer> personScore = new HashMap<>();
    File file = new File("score_label.txt");
    JTextField scoreField = new JTextField("wpisz imie");
    JButton saveScore = new JButton("SAve score");
    int score;


     scoreLabel(int score) {
   //     JFrame frameOver = new JFrame();

        this.setVisible(true);
         this.add(scoreField);
         this.add(saveScore);

         this.setFocusable(true);

// add content pane i zrobic go

        this.score = score;
        saveScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    saveScore();
                } catch (IOException ex ) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
     }
     void saveScore() throws IOException, ClassNotFoundException {
         if(file.exists()){


             FileInputStream fileInputStream = new FileInputStream("score_label.txt");

             ObjectInputStream objectInputStream = null;
             try {
                 objectInputStream = new ObjectInputStream(fileInputStream);
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
             Object[][] scoreTab = (Object[][])objectInputStream.readObject();

 /*
            String[][] scoreTab = new String[10][2];
             scoreTab[1][0]="maka";
             scoreTab[1][1]="6";
             scoreTab[2][0]="penn";
             scoreTab[2][1]="3";
             scoreTab[3][0]="siu";
             scoreTab[3][1]="9";
             scoreTab[4][0]="kut";
             scoreTab[4][1]="1";
             scoreTab[5][0]="dup";
             scoreTab[5][1]="4";
             scoreTab[6][0]="huu";
             scoreTab[6][1]="8";
             scoreTab[7][0]="na";
             scoreTab[7][1]="6";
             scoreTab[8][0]="Mareczek";
             scoreTab[8][1]="34";
             scoreTab[9][0]="Kent";
             scoreTab[9][1]="123";
             scoreTab[0][0]="James";
             scoreTab[0][1]="1";
*/
             //scoreTab[1][1]= new String[]{{"James"},{"7"}};

             //deklaracja tablic pomocniczych
             String[] stringTab = new String[scoreTab.length];
             int[] intTab = new int[scoreTab.length];
             for(int i=0;i<scoreTab.length;i++){
                 stringTab[i]= String.valueOf(scoreTab[i][0]);
                 intTab[i] = Integer.valueOf(String.valueOf(scoreTab[i][1]));
                 System.out.println(stringTab[i]+intTab[i]);
             }
             //bubble sort
             for(int i=0;i<scoreTab.length;i++){
                 for(int j=i+1;j<scoreTab.length;j++) {

                     if ( intTab[i]> intTab[j]) {
                         int tempInt = intTab[i];
                         String tempString = stringTab[i];
                         stringTab[i] = stringTab[j];
                         intTab[i] = intTab[j];

                         stringTab[j] = tempString;
                         intTab[j] = tempInt;
                     }
                 }
             }


            //zamiana na nowy wynik
            int k=0;
             while((score>intTab[k])&&(k<scoreTab.length-1)){
                 k++;
             }
             if(score>intTab[0]){


                 for(int i=0;i<k-1;i++){
                     intTab[i]=intTab[i+1];
                     stringTab[i]=stringTab[i+1];
                 }
                 intTab[k-1]=score;
                 stringTab[k-1]=scoreField.getText();


             }



             System.out.println("wyspisuje");
             for(int i=0;i< scoreTab.length;i++){
                 System.out.println(stringTab[i]+" "+intTab[i]);
             }

             //zapisywanie 2 tablic do 1 spowrotem
             for(int i=0;i<scoreTab.length;i++){
                 scoreTab[i][0] = stringTab[i];
                 scoreTab[i][1] =  String.valueOf(intTab[i]);
             }

             try {
                 FileOutputStream fileOutputStream = new FileOutputStream("score_label.txt");

                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(scoreTab);

                 objectOutputStream.close();
                 fileOutputStream.close();

             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

         }

     }
}

