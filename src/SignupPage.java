import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignupPage extends JFrame{
    private JPanel panel1;
    private JTextField fEmail;
    private JTextField fName;
    private JPasswordField fPassword;
    private JButton goBackButton;
    private JButton createButton;
    private JFrame frame;
    private AppData usersData;

    public SignupPage(AppData usersData) {
        this.usersData = usersData;
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
                new LoginPage(usersData);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        fPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    registerUser();
                }
            }
        });
    }

    public void registerUser(){
        String name = fName.getText();
        String email = fEmail.getText();
        String password = String.valueOf(fPassword.getPassword());
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (User user : usersData.getAccounts()){
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
        this.usersData.getAccounts().add(newProfile);
        frame.dispose();
        new MainMenuPage(usersData);
    }
}

