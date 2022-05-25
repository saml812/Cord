import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProfilesPage extends JFrame{
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
    private JTextArea searchResults;
    private User selectedProfile;
    private JTextField choiceText;
    private JLabel choiceLabel;
    private JLabel selectedUserName;
    private JLabel selectedUserEmail;
    private JLabel selectedUserAge;
    private JLabel friendRequest;
    private JLabel selectedUser;
    private JTextArea selectedUserFriends;
    private JTextArea selectedUserHobbies;
    private JLabel FName;
    private JLabel FName1;
    private JScrollPane Scroll1;
    private JScrollPane Scroll2;
    private JFrame frame;
    private User account = null;
    private ServerData server;
    private ArrayList<User> searchedProfiles = null;

    public ProfilesPage(ServerData server) {
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
                new FeedPage(server);
            }
        });

        Messages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new MessagesPage(server);
            }
        });

        Profiles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new ProfilesPage(server);
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
                new MainMenuPage(server);
            }
        });


        displayUsername.setText("Username: " + account.getName());
        displayEmail.setText("Email: " + account.getEmail());
        displayPassword.setText("Password: " + account.getPassword());
        displayAge.setText("Age: " + account.getAge());
        updateFriends();
        updateHobbies();
        choiceLabel.setVisible(false);
        choiceText.setVisible(false);
        selectedUserFriends.setVisible(false);
        selectedUserHobbies.setVisible(false);
        FName.setVisible(false);
        FName1.setVisible(false);
        Scroll1.setVisible(false);
        Scroll2.setVisible(false);
        friendRequest.setVisible(false);

        changeUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String newName = changeUsername.getText();
                    changeUsername.setText("");
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
                    changeEmail.setText("");
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
                    changePassword.setText("");
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
                    changeAge.setText("");
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
                    searchResults.setText("");
                    choiceLabel.setVisible(false);
                    choiceText.setVisible(false);
                    selectedUserFriends.setVisible(false);
                    selectedUserHobbies.setVisible(false);
                    FName.setVisible(false);
                    FName1.setVisible(false);
                    Scroll1.setVisible(false);
                    Scroll2.setVisible(false);
                    friendRequest.setVisible(false);
                    selectedUser.setVisible(false);
                    selectedUserName.setVisible(false);
                    selectedUserEmail.setVisible(false);
                    selectedUserAge.setVisible(false);
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

        choiceText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = choiceText.getText();
                    choiceText.setText("");
                    int number = 0;
                    try {
                        number = Integer.parseInt(text);
                        System.out.println(number);
                    } catch(NumberFormatException c){
                        System.out.println("hello");
                        errorMessage_NOCHOICE();
                        return;
                    }
                    if (number > searchedProfiles.size()){
                        System.out.println("yo");
                        errorMessage_NOCHOICE();
                    }
                    else{
                        selectedProfile = searchedProfiles.get(number-1);
                        System.out.println(selectedProfile.getName());
                        displayUSERINFO(selectedProfile);
                    }
                }
            }
        });

        friendRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

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
        searchResults.setText("");
        ArrayList<User> profiles = new ArrayList<User>();
        for (int i = 0; i < server.getAccounts().size(); i++)
        {
            String profileName = server.getAccounts().get(i).getName();
            profileName = profileName.toLowerCase();

            if (profileName.contains(userName))
            {
                profiles.add(server.getAccounts().get(i));
            }
        }

        if (profiles.size() == 0){
            searchResults.append("No users found");
            choiceLabel.setVisible(false);
            choiceText.setVisible(false);
        }
        else
        {
            choiceLabel.setVisible(true);
            choiceText.setVisible(true);
            for (int i = 0; i < profiles.size(); i++)
            {
                String profileName = profiles.get(i).getName();
                int choiceNum = i + 1;
                searchResults.append(choiceNum + ". " + profileName + "\n");
            }

            searchedProfiles = profiles;
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

    public void errorMessage_NOCHOICE(){
        JOptionPane.showMessageDialog(this, "Please enter a valid choice", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void displayUSERINFO(User selected){
        selectedUserFriends.setVisible(true);
        selectedUserHobbies.setVisible(true);
        FName.setVisible(true);
        FName1.setVisible(true);
        Scroll1.setVisible(true);
        Scroll2.setVisible(true);
        friendRequest.setVisible(true);
        selectedUser.setVisible(true);
        selectedUserName.setVisible(true);
        selectedUserEmail.setVisible(true);
        selectedUserAge.setVisible(true);
        selectedUser.setText(selected.getName() + "'s Profile");
        selectedUserName.setText("Name: " + selected.getName());
        selectedUserEmail.setText("Email: " + selected.getEmail());
        selectedUserAge.setText("Age: " + selected.getAge());

        selectedUserFriends.selectAll();
        selectedUserFriends.replaceSelection("");
        if (selectedProfile.getFriends().size() > 0){
            for (int i = 0; i < selected.getFriends().size(); i++){
                selectedUserFriends.append(i+1 + ". " + selected.getFriends().get(i).getName() + "\n");
            }
        }
        else {
            selectedUserFriends.append(selected.getName() + " has no friends");
        }

        selectedUserHobbies.selectAll();
        selectedUserHobbies.replaceSelection("");
        if (selected.getHobbies().size() > 0){
            for (int i = 0; i < selected.getHobbies().size(); i++){
                selectedUserHobbies.append(i+1 + ". " + selected.getHobbies().get(i) + "\n");
            }
        }
        else {
            selectedUserHobbies.append(selected.getName() + " has no hobbies");
        }
    }


}
