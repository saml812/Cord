import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame{
    private JTextField email;
    private JPanel panel1;
    private JPasswordField password;
    private JButton logIn;
    private JButton signUp;
    private JFrame frame;

    public LoginPage(){
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignupPage().setVisible(true);
            }
        });


    }
}
