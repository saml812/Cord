import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private JTextField deleteHobby;
    private JPanel searchResults;
    private JPanel selectedProfile;
    private JFrame frame;
    private User account = null;
    private Data server;
    private JLabel noResults;

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
                    if (newName.equals("")){
                        errorMessage_NONAME();
                    }
                    else{
                        account.setName(newName);
                    }
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
                    if (newEmail.equals("")){
                        errorMessage_NOEMAIL();
                    }
                    else{
                        account.setEmail(newEmail);
                    }
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
                    if (newPassword.equals("")){
                        errorMessage_NOPASSWORD();
                    }
                    else{
                        account.setPassword(newPassword);
                    }
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
                    if (newAge.equals("")){
                        errorMessage_NOAGE();
                    }
                    else{
                        account.setAge(Integer.parseInt(newAge));
                    }
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
                    String[] lines = hobbyList.getText().split("\n");
                    if (lines[lines.length-1].equals("")){
                        errorMessage_NOHOBBY();
                    }
                    else{
                        account.getHobbies().add(lines[lines.length-1]);
                        hobbyList.selectAll();
                        hobbyList.replaceSelection("");
                        updateHobbies();
                    }
                }
            }
        });

        deleteHobby.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String hobbyRemove = deleteHobby.getText();
                    deleteHobby.setText("");
                    if (hobbyRemove.equals("")){
                        errorMessage_NOHOBBY();
                    }
                    else{
                        removeHobbies(hobbyRemove);
                    }
                }
            }
        });

        searchedUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String userName = searchedUser.getText();
                    searchedUser.setText("");
                    if (userName.equals("")){
                        errorMessage_NONAME();
                    }
                    else{
                        displayResults(userName);
                    }
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
        if (account.getHobbies().size() > 0){
            for (int i = 0; i < account.getHobbies().size(); i++){
                hobbyList.append(account.getHobbies().get(i) + "\n");
            }
        }
        else {
            hobbyList.append("You have no hobbies");
        }
    }

    public void removeHobbies(String removeHobby){
        if (!(account.getHobbies().contains(removeHobby))){
            JOptionPane.showMessageDialog(this, "This hobby does not exist", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            for (int i = 0; i < account.getHobbies().size(); i++){
                if (removeHobby.equals(account.getHobbies().get(i))){
                    account.getHobbies().remove(i);
                    i--;
                }
            }
            hobbyList.selectAll();
            hobbyList.replaceSelection("");
            updateHobbies();
        }
    }

    public void displayResults(String userName){
        ArrayList<User> searchedProfiles = new ArrayList<User>();
        for (int i = 0; i < server.getAccounts().size(); i++)
        {
            String profileName = server.getAccounts().get(i).getName();
            profileName = profileName.toLowerCase();

            if (profileName.contains(userName))
            {
                searchedProfiles.add(server.getAccounts().get(i));
            }
        }

        noResults = new JLabel("");
        noResults.setText("No usernames found");
        if (searchedProfiles.size() == 0){
            searchResults.add(noResults);
        }
    }


    public void errorMessage_NOHOBBY(){
        JOptionPane.showMessageDialog(this, "Please enter a hobby", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void errorMessage_NONAME(){
        JOptionPane.showMessageDialog(this, "Please enter a username", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void errorMessage_NOEMAIL(){
        JOptionPane.showMessageDialog(this, "Please enter a email", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void errorMessage_NOPASSWORD(){
        JOptionPane.showMessageDialog(this, "Please enter a password", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void errorMessage_NOAGE(){
        JOptionPane.showMessageDialog(this, "Please enter a age", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

}
