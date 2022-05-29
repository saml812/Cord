import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage extends JFrame{
    private JTextField fEmail;
    private JPanel panel1;
    private JPasswordField fPassword;
    private JButton logIn;
    private JButton signUp;
    private JFrame frame;
    private AppData usersData;
    private User user = null;

    public LoginPage(AppData usersData){
        this.usersData = usersData;
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
                new SignupPage(usersData);
            }
        });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        fPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginUser();
                }
            }
        });
    }

    public void loginUser(){
        String email = fEmail.getText();
        String password = String.valueOf(fPassword.getPassword());
        if (email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (User user : usersData.getAccounts()){
            if (user.getEmail().equals(email)){
                this.user = user;
            }
        }
        if (user != null){
            if (user.getPassword().equals(password)){
                user.setLoggedIn(true);
                new MainMenuPage(usersData);
                frame.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "This password is incorrect", "Try again", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "This email does not exist", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }
}
