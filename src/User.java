import java.util.*;

public class User {
    private String name;
    private String email;
    private String password;
    private int age;
    private ArrayList<User> friends;
    private ArrayList<String> textHistory;
    private ArrayList<String> hobbies;
    private ArrayList<String> feedHistory;
    private boolean loggedIn;

    public User(String name, String email, String password, int age){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        friends = new ArrayList<User>();
        textHistory = new ArrayList<String>();
        hobbies = new ArrayList<String>();
        feedHistory = new ArrayList<String>();
        loggedIn = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setTextHistory(ArrayList<String> textHistory) {
        this.textHistory = textHistory;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void setFeedHistory(ArrayList<String> feedHistory) {
        this.feedHistory = feedHistory;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<String> getTextHistory() {
        return textHistory;
    }

    public ArrayList<String> getFeedHistory() {
        return feedHistory;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void displayInfo(){
        System.out.println("------------ " + getName() + "'s profile ----------");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        String Hobbies = "Hobbies: ";
        if (getHobbies().size() == 0){
            Hobbies += getName() + " has no hobbies";
        }
        else
        {
            for (String hobby : getHobbies()){
                Hobbies += hobby + ", ";
            }
            Hobbies.substring(0,Hobbies.length()-3);
        }
        System.out.println(Hobbies);
        String Friends = "Friends: ";
        if (getFriends().size() == 0){
            Friends += getName() + " has no friends";
        }
        else
        {
            for (User friend : getFriends()){
                Friends += friend.getName() + ", ";
            }
            Friends.substring(0,Friends.length()-3);
        }
        System.out.println(Friends);
    }
}
