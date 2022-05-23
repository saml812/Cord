import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage extends JFrame{
    private JPanel panel1;
    private JTextField femail;
    private JTextField fname;
    private JPasswordField fpassword;
    private JButton goBackButton;
    private JButton createButton;
    private JFrame frame;
    private Data server;

    public SignupPage(Data server) {
        this.server = server;
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginPage(server);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    public void registerUser(){
        String name = fname.getText();
        String email = femail.getText();
        String password = String.valueOf(fpassword.getPassword());
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (User user : server.getAccounts()){
            if (user.getName().equals(name)){
                JOptionPane.showMessageDialog(this, "This username already exist", "Try again", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (user.getEmail().equals(email)){
                JOptionPane.showMessageDialog(this, "This email already exist", "Try again", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        User newProfile = new User(name, email, password);
        newProfile.setLoggedIn(true);
        this.server.getAccounts().add(newProfile);
        frame.dispose();
        new Dashboard(server);
    }
}

