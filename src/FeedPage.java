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
    private ServerData server;

    public FeedPage(ServerData server) {
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
        updateFeed();

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

        feedText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    if (server.getActiveFeed().size() == 0){
                        FeedBox.setText("");
                    }
                    String text = feedText.getText() + "~ " + account.getName();
                    server.getActiveFeed().add(text);
                    account.getFeedHistory().add(text);
                    FeedBox.append("- " + text.substring(0, text.indexOf("~ ")) + " by: " + account.getName() + "\n");
                    feedText.setText("");
                }
            }
        });

    }

    public void updateFeed(){
        if (server.getActiveFeed().size() > 0){
            for (String post : server.getActiveFeed()) {
                for (User account : server.getAccounts()){
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
