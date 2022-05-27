import javax.swing.*;
import java.awt.*;

public class TextLeft extends JPanel{
    private JPanel panel1;
    private JTextPane textPane1;

    public TextLeft(){
        panel1.setSize(new Dimension(200, 200));
        panel1.add(textPane1);
        panel1.setVisible(true);
        textPane1.setText("Hello world!");
    }
}
