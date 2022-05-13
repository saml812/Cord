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
}
