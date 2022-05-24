import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard3 extends JFrame{
    private JPanel panel1;
    private JLabel displayName;
    private JLabel Feed;
    private JLabel Messages;
    private JLabel Profiles;
    private JLabel logOut;
    private JPanel JPanel;
    private JPanel JPanel1;
    private JPanel JPanel2;
    private JPanel JPanel3;
    private JPanel JPanel4;
    private JPanel MenuPanel;
    private JPanel UserProfile;
    private JPanel SearchProfiles;
    private JTextField searchedUser;
    private JPanel SearchedProfile;
    private JTextField changeUsername;
    private JTextField changeEmail;
    private JPasswordField changePassword;
    private JTextField changeAge;
    private JLabel displayAge;
    private JLabel displayPassword;
    private JLabel displayEmail;
    private JLabel displayUsername;
    private JTextArea friendList;
    private JTextArea hobbyList;
    private JTextArea searchedUser_hobbyList;
    private JTextArea searchedUser_friendList;
    private JLabel searchedUsername;
    private JLabel searchedUseremail;
    private JLabel searchedUserage;
    private JFrame frame;
    private User account = null;
    private Data server;

    public Dashboard3(Data server) {
        this.server = server;
        for (User user :  this.server.getAccounts()){
            if (user.isLoggedIn()){
                account = user;
            }
        }

        frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        displayName.setText("Hello " + account.getName());

        Feed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new Dashboard1(server);
            }
        });

        Messages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new Dashboard2(server);
            }
        });

        Profiles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new Dashboard3(server);
            }
        });

        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                account.setLoggedIn(false);
                Saver.writeToFile(server.getAccounts(), server.activeFeed);
                new LoginPage(server);
            }
        });

        JPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new Dashboard(server);
            }
        });


        displayUsername.setText("Username: " + account.getName());
        displayEmail.setText("Email: " + account.getEmail());
        displayPassword.setText("Password: " + account.getPassword());
        displayAge.setText("Age: " + account.getAge());
        updateFriends();
        updateHobbies();

        changeUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String newName = changeUsername.getText();
                    account.setName(newName);
                    displayUsername.setText("Username: " + account.getName());
                    displayEmail.setText("Email: " + account.getEmail());
                    displayPassword.setText("Password: " + account.getPassword());
                    displayAge.setText("Age: " + account.getAge());
                }
            }
        });

        changeEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String newEmail = changeEmail.getText();
                    account.setEmail(newEmail);
                    displayUsername.setText("Username: " + account.getName());
                    displayEmail.setText("Email: " + account.getEmail());
                    displayPassword.setText("Password: " + account.getPassword());
                    displayAge.setText("Age: " + account.getAge());
                }
            }
        });

        changePassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String newPassword = String.valueOf(changePassword.getPassword());
                    account.setPassword(newPassword);
                    displayUsername.setText("Username: " + account.getName());
                    displayEmail.setText("Email: " + account.getEmail());
                    displayPassword.setText("Password: " + account.getPassword());
                    displayAge.setText("Age: " + account.getAge());
                }
            }
        });

        changeAge.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String newAge = changeAge.getText();
                    account.setAge(Integer.parseInt(newAge));
                    displayUsername.setText("Username: " + account.getName());
                    displayEmail.setText("Email: " + account.getEmail());
                    displayPassword.setText("Password: " + account.getPassword());
                    displayAge.setText("Age: " + account.getAge());
                }
            }
        });

        hobbyList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String[] lines = hobbyList.getText().split("\\n");

                    account.getHobbies().add(hobbyList.getText());
                    updateHobbies();
                }
            }
        });

        hobbyList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    for (int i = 0; i < account.getHobbies().size(); i++){
                        if (account.getHobbies().get(i).equals(hobbyList.getText())){
                            account.getHobbies().remove(i);
                            i--;
                        }
                    }
                    updateHobbies();
                }
            }
        });
    }

    public void updateFriends(){
        friendList.selectAll();
        friendList.replaceSelection("");
        if (account.getFriends().size() > 0){
            for (int i = 0; i < account.getFriends().size(); i++){
                friendList.append(i+1 + ". " + account.getFriends().get(i).getName() + "\n");
            }
        }
        else {
            friendList.append("You have no friends");
        }
    }

    public void updateHobbies(){
        hobbyList.selectAll();
        hobbyList.replaceSelection("");
        if (account.getHobbies().size() > 0){
            for (int i = 0; i < account.getHobbies().size(); i++){
                hobbyList.append(i+1 + ". " + account.getHobbies().get(i) + "\n");
            }
        }
        else {
            friendList.append("You have no hobbies");
        }
    }
}
