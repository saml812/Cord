import java.util.ArrayList;

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
}
