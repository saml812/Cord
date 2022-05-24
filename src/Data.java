import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    ArrayList<User> accounts = new ArrayList<User>();
    ArrayList<String> activeFeed = new ArrayList<String>();
    User user = null;
    User selectedProfile = null;

    public ArrayList<User> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<User> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<String> getActiveFeed() {
        return activeFeed;
    }

    public void setActiveFeed(ArrayList<String> activeFeed) {
        this.activeFeed = activeFeed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(User selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public void loadData() throws FileNotFoundException {
        File myObj = new File("src/Database");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.contains("User")){
                String[] userData = data.split("\\|");
                ArrayList<String> hobbies = new ArrayList<String>();
                ArrayList<User> friends = new ArrayList<User>();
                ArrayList<String> textHistory = new ArrayList<String>();
                ArrayList<String> feedHistory = new ArrayList<String>();
                ArrayList<User> incomingRequests = new ArrayList<User>();
                ArrayList<User> outgoingRequests = new ArrayList<User>();

                if (!(userData[5].equals("null"))) {
                    String[] hobbyList = userData[5].split(",");
                    for (int i = 0; i < hobbyList.length; i++) {
                        hobbies.add(hobbyList[i]);
                    }
                }

                if (!(userData[7].equals("null"))) {
                    String[] feedTexts = userData[7].split("/");
                    for (int i = 0; i < feedTexts.length; i++) {
                        feedHistory.add(feedTexts[i]);
                    }
                }

                User newProfile = new User(userData[1], userData[2], userData[3], Integer.parseInt(userData[4]), hobbies, feedHistory);
                accounts.add(newProfile);
                System.out.println(newProfile.getFeedHistory().get(0));
            }
            if (data.contains("Feed")){
                String[] driveData = data.split("\\|");
                activeFeed.add(driveData[1]);
            }


        }
        myReader.close();
    }
}
