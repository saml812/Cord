import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppData {
    ArrayList<User> accounts = new ArrayList<User>();
    ArrayList<String> activeFeed = new ArrayList<String>();
    User user = null;
    User selectedUser = null;

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
        return selectedUser;
    }

    public void setSelectedProfile(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void loadData() throws FileNotFoundException {

        File myObj = new File("src/Database");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.contains("User")){
                String[] userData = data.split("\\|");
                ArrayList<String> hobbies = new ArrayList<String>();
                ArrayList<String> textHistory = new ArrayList<String>();
                ArrayList<String> feedHistory = new ArrayList<String>();

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

                if (!(userData[8].equals("null"))) {
                    String[] privateTexts = userData[8].split("\\\\");
                    for (int i = 0; i < privateTexts.length; i++) {
                        textHistory.add(privateTexts[i]);
                    }
                }

                User newProfile = new User(userData[1], userData[2], userData[3], Integer.parseInt(userData[4]), hobbies, feedHistory, textHistory);
                accounts.add(newProfile);
            }

            if (data.contains("Feed")){
                String[] feedData = data.split("\\|");
                activeFeed.add(feedData[1]);
            }

            if (data.contains("Add")){
                String[] addData = data.split("\\|");

                for (User user : accounts){
                    if (user.getName().equals(addData[1])){
                        for (User user1 : accounts){
                            String[] friendsToAdd = addData[2].split(",");
                            for (String s : friendsToAdd) {
                                if (user1.getName().equals(s)) {
                                    user.getFriends().add(user1);
                                }
                            }
                            String[] incomingRequestsToAdd = addData[3].split(",");
                            for (String s : incomingRequestsToAdd) {
                                if (user1.getName().equals(s)) {
                                    user.getIncomingRequests().add(user1);
                                }
                            }
                            String[] outgoingRequestsToAdd = addData[4].split(",");
                            for (String s : outgoingRequestsToAdd) {
                                if (user1.getName().equals(s)) {
                                    user.getOutgoingRequests().add(user1);
                                }
                            }
                        }
                    }
                }
            }
        }
        myReader.close();
    }
}
