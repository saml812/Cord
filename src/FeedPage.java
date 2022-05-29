import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FeedPage extends JFrame{
    private JPanel panel1;
    private JLabel displayName;
    private JLabel Feed;
    private JLabel Messages;
    private JLabel Profiles;
    private JLabel logOut;
    private JPanel JPanel;
    private JTextField feedText;
    private JTextArea FeedBox;
    private JFrame frame;
    private User account = null;
    private AppData usersData;

    public FeedPage(AppData usersData) {
        this.usersData = usersData;
        for (User user :  this.usersData.getAccounts()){
            if (user.isLoggedIn()){
                account = user;
            }
        }

        frame = new JFrame("Feed");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setResizable(false);
        frame.add(panel1);
        frame.setVisible(true);
        displayName.setText("Hello " + account.getName());
        updateFeed();

        Feed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new FeedPage(usersData);
            }
        });

        Messages.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new MessagesPage(usersData);
            }
        });

        Profiles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new ProfilesPage(usersData);
            }
        });

        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                account.setLoggedIn(false);
                AppSaver.writeToFile(usersData.getAccounts(), usersData.activeFeed);
                new LoginPage(usersData);
            }
        });

        JPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new MainMenuPage(usersData);
            }
        });

        feedText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    if (usersData.getActiveFeed().size() == 0){
                        FeedBox.setText("");
                    }
                    String text = feedText.getText() + "~ " + account.getName();
                    usersData.getActiveFeed().add(text);
                    account.getFeedHistory().add(text);
                    FeedBox.append("- " + text.substring(0, text.indexOf("~ ")) + " by: " + account.getName() + "\n");
                    feedText.setText("");
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);

    }

    public void updateFeed(){
        if (usersData.getActiveFeed().size() > 0){
            for (String post : usersData.getActiveFeed()) {
                for (User account : usersData.getAccounts()){
                    for (int i = 0; i < account.getFeedHistory().size(); i++){
                        if (account.getFeedHistory().get(i).equals(post)){
                            FeedBox.append("- " + post.substring(0, post.indexOf("~ ")) + " by: " + account.getName() + "\n");
                        }
                    }
                }
            }
        }
        else {
            FeedBox.append("The feed is empty!");
        }
    }
}
