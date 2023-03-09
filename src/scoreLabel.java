import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
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

                saveScore();

            }
        });
     }
     void saveScore() {
         try {
             FileInputStream fileInputStream = new FileInputStream("score_label.txt");

             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
             HashMap hashMap = (HashMap)objectInputStream.readObject();


             fileInputStream.close();
             objectInputStream.close();

                hashMap.put(scoreField.getText(),score);




             FileOutputStream fileOutputStream =new FileOutputStream("score_label.txt");

             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

             objectOutputStream.writeObject(hashMap);
             objectOutputStream.close();
             fileOutputStream.close();


         } catch (IOException | ClassNotFoundException e) {
             throw new RuntimeException(e);
         }

     }
}
class hashTry implements Serializable{


}

