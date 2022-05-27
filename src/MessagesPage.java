import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MessagesPage extends JFrame{
    private JPanel panel1;
    private JLabel displayName;
    private JLabel Feed;
    private JLabel Messages;
    private JLabel Profiles;
    private JLabel logOut;
    private JPanel JPanel;
    private JTextArea messageBlock;
    private JTextArea messageField;
    private JTextArea friendsBlock;
    private JTextField choiceField;
    private JLabel userDM;
    private JScrollPane scrollBar;
    private JFrame frame;
    private User account = null;
    private AppData server;
    private User selectedProfile = null;

    public MessagesPage(AppData server) {
        this.server = server;
        for (User user :  this.server.getAccounts()){
            if (user.isLoggedIn()){
                account = user;
            }
        }

        frame = new JFrame("Messages");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        displayName.setText("Hello " + account.getName());
        updateFriends();
        messageBlock.setVisible(false);
        messageField.setVisible(false);
        scrollBar.setVisible(false);

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
                AppSaver.writeToFile(server.getAccounts(), server.activeFeed);
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

        choiceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String text = choiceField.getText();
                    choiceField.setText("");
                    int number = 0;
                    try {
                        number = Integer.parseInt(text);
                    } catch(NumberFormatException c){
                        errorMessage_NOCHOICE();
                        return;
                    }
                    if (number > account.getFriends().size()){
                        errorMessage_NOCHOICE();
                    }
                    else{
                        selectedProfile = account.getFriends().get(number-1);
                        displayMessages(selectedProfile);
                    }
                }
            }
        });
    }

    public void updateFriends(){
        friendsBlock.selectAll();
        friendsBlock.replaceSelection("");
        if (account.getFriends().size() > 0){
            for (int i = 0; i < account.getFriends().size(); i++){
                friendsBlock.append(i+1 + ". " + account.getFriends().get(i).getName() + "\n");
            }
        }
        else {
            friendsBlock.append("You have no friends");
        }
    }

    public void errorMessage_NOCHOICE(){
        JOptionPane.showMessageDialog(this, "Please enter a valid choice", "Try again", JOptionPane.ERROR_MESSAGE);
        return;
    }

    public void displayMessages(User user){
        messageBlock.setVisible(true);
        messageField.setVisible(true);
        scrollBar.setVisible(true);
    }

}
