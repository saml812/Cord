import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;

public class MessagesPage extends JFrame{
    private JPanel panel1;
    private JLabel displayName;
    private JLabel Feed;
    private JLabel Messages;
    private JLabel Profiles;
    private JLabel logOut;
    private JPanel JPanel;
    private JTextArea messageField;
    private JTextField choiceField;
    private JLabel userDM;
    private JScrollPane scrollBar;
    private JFrame frame;
    private JTextArea friendsBlock;
    private JTextArea messageBlock;
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
                        displayMessages();
                    }
                }
            }
        });

        messageField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                messageField.setText("");
            }
        });

        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String comment = messageField.getText();
                    messageField.setText("");
                    account.getTextHistory().add(comment + "/" + account.getName() + "/" + selectedProfile.getName());
                    selectedProfile.getTextHistory().add(comment + "/" + account.getName() + "/" + selectedProfile.getName());

                    displayMessages();
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
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

    public void displayMessages(){
        messageBlock.setText("");
        messageBlock.setVisible(true);
        messageField.setVisible(true);
        scrollBar.setVisible(true);

        userDM.setText(selectedProfile.getName() + "'s messages");

            for (int i = 0; i < account.getTextHistory().size(); i++){
                String between = account.getTextHistory().get(i).substring(account.getTextHistory().get(i).indexOf("/")+1);
                if (between.equals(account.getName() + "/" + selectedProfile.getName()) || between.equals(selectedProfile.getName() + "/" + account.getName())){
                    if (between.substring(0,between.indexOf("/")).equals(account.getName())){

                        String message = "";
                        String splitMessage = "";
                        String actualMessage = account.getTextHistory().get(i).substring(0,account.getTextHistory().get(i).indexOf("/"));
                        String copyMessage = actualMessage;

                        if (actualMessage.length() > 65){
                            while (copyMessage.length() > 65){
                                String lastWord = copyMessage.substring(0, 65);
                                String newMessage = copyMessage.substring(0, lastWord.lastIndexOf(" ")) + "|";

                                splitMessage += newMessage;
                                copyMessage = copyMessage.substring(lastWord.lastIndexOf(" "));
                            }
                            String[] splits = splitMessage.split("\\|");

                            for (int j = 0; j < 65-splits[0].length(); j++){
                                message += " ";
                            }

                            for (int a = 0; a < splits.length; a++){
                                if (a == 0){
                                    messageBlock.append(message + splits[a] + " <--  " + "\n");
                                }
                                else{
                                    messageBlock.append(message + splits[a] + "     " + "\n");
                                }
                            }
                        }
                        else{
                            for (int j = 0; j < 65-actualMessage.length(); j++){
                                message += " ";
                            }
                            messageBlock.append(message + actualMessage + " <--" + "\n");
                        }
                    }
                    else
                    {
                        String actualMessage = account.getTextHistory().get(i).substring(0,account.getTextHistory().get(i).indexOf("/"));
                        String splitMessage = "";
                        String copyMessage = actualMessage;

                        if (actualMessage.length() > 65){
                            while (copyMessage.length() > 65){
                                String lastWord = copyMessage.substring(0, 65);
                                String newMessage = copyMessage.substring(0, lastWord.lastIndexOf(" ")) + "|";

                                splitMessage += newMessage;
                                copyMessage = copyMessage.substring(lastWord.lastIndexOf(" "));
                            }
                            String[] splits = splitMessage.split("\\|");

                            for (int a = 0; a < splits.length; a++){
                                if (a == 0){
                                    messageBlock.append("--> " + splits[a] + "\n");
                                }
                                else{
                                    messageBlock.append("   "  + splits[a] + "\n");
                                }
                            }
                        }
                        else{
                            messageBlock.append("--> "  + actualMessage + "\n");
                        }
                    }
                }
            }
    }
}
