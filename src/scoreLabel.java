import javax.swing.*;

public class scoreLabel extends JFrame {

    scoreLabel() {
        JFrame frameOver = new JFrame();
        frameOver.setBounds(100, 50, 200, 200);
        frameOver.setResizable(true);
        frameOver.setDefaultCloseOperation(3);
        frameOver.setVisible(true);
        frameOver.add(new JLabel("Koniec Gry"));
        JTextField nameField = new JTextField("Wpisz swoje imie");
        frameOver.add(nameField);
        System.out.println(nameField.getText());
    }


}
