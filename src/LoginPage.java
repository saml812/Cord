import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame{
    private JTextField femail;
    private JPanel panel1;
    private JPasswordField fpassword;
    private JButton logIn;
    private JButton signUp;
    private JFrame frame;
    private Data server;
    private User profile = null;

    public LoginPage(Data server){
        this.server = server;
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
                new SignupPage(server);
            }
        });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
    }

    public void loginUser(){
        String email = femail.getText();
        String password = String.valueOf(fpassword.getPassword());
        if (email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (User user : server.getAccounts()){
            if (user.getEmail().equals(email)){
                profile = user;
            }
        }
        if (profile != null){
            if (profile.getPassword().equals(password)){
                profile.setLoggedIn(true);
                new Dashboard(server);
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
