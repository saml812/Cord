import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPage extends JFrame{
    private JPanel panel1;
    private JLabel displayName;
    private JLabel Feed;
    private JLabel Messages;
    private JLabel Profiles;
    private JLabel logOut;
    private JPanel JPanel;
    private JFrame frame;
    private User account = null;
    private AppData usersData;
    private JLabel errorMessage;

    public MainMenuPage(AppData usersData) {
        this.usersData = usersData;
        for (User user :  this.usersData.getAccounts()){
            if (user.isLoggedIn()){
                account = user;
            }
        }

        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setResizable(false);
        frame.add(panel1);
        frame.setVisible(true);
        displayName.setText("Hello " + account.getName());

        if (account.getAge() == 0){
            errorMessage.setText("Please set your age before signing out");
        }
        else{
            errorMessage.setText("");
        }

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

        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
