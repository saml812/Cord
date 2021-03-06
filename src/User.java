import java.util.*;

public class User {
    private String name;
    private String email;
    private String password;
    private int age;
    private ArrayList<User> friends;
    private ArrayList<User> incomingRequests;
    private ArrayList<User> outgoingRequests;
    private ArrayList<String> textHistory;
    private ArrayList<String> hobbies;
    private ArrayList<String> feedHistory;
    private boolean loggedIn;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        age = 0;
        friends = new ArrayList<User>();
        textHistory = new ArrayList<String>();
        hobbies = new ArrayList<String>();
        feedHistory = new ArrayList<String>();
        incomingRequests = new ArrayList<User>();
        outgoingRequests = new ArrayList<User>();
        loggedIn = false;
    }

    public User(String name, String email, String password, int age){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        friends = new ArrayList<User>();
        textHistory = new ArrayList<String>();
        hobbies = new ArrayList<String>();
        feedHistory = new ArrayList<String>();
        incomingRequests = new ArrayList<User>();
        outgoingRequests = new ArrayList<User>();
        loggedIn = false;
    }

    public User(String name, String email, String password, int age, ArrayList<String> hobbies, ArrayList<String> feedHistory, ArrayList<String> textHistory){
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        friends = new ArrayList<User>();
        this.textHistory = textHistory;
        this.hobbies = hobbies;
        this.feedHistory = feedHistory;
        incomingRequests = new ArrayList<User>();
        outgoingRequests = new ArrayList<User>();
        loggedIn = false;
    }

    public ArrayList<User> getIncomingRequests() {
        return incomingRequests;
    }

    public void setIncomingRequests(ArrayList<User> incomingRequests) {
        this.incomingRequests = incomingRequests;
    }

    public ArrayList<User> getOutgoingRequests() {
        return outgoingRequests;
    }

    public void setOutgoingRequests(ArrayList<User> outgoingRequests) {
        this.outgoingRequests = outgoingRequests;
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
