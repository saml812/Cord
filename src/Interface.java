import com.sun.net.httpserver.Authenticator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Interface {
    ArrayList<User> accounts = new ArrayList<User>();
    ArrayList<String> activeFeed = new ArrayList<String>();
    User user = null;
    User selectedProfile = null;

    public void start(){

        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Cord!");
        System.out.println("1) Sign in");
        System.out.println("2) Sign up");
        System.out.println("3) Exit");
        System.out.print("Enter choice: ");
        String userChoice = s.nextLine();

        if (userChoice.equals("1")){
            signIn();
        }
        if (userChoice.equals("2")){
            signUp();
        }
        if (userChoice.equals("3")){
            System.out.println("Saving data. Good-bye!");
            Saver.writeToFile(accounts, activeFeed);
        }
    }

    public void run(){
        for (User account : accounts){
            if (account.isLoggedIn()){
                user = account;
            }
        }
        System.out.println("Welcome " + user.getName() + "! The greatest media platform ever created!\nDisclaimer: no lol");
        Scanner s = new Scanner(System.in);
        String menuOption = "";
        while (!menuOption.equals("s"))
        {
            System.out.println();
            System.out.println("------------ Main Menu ----------");
            System.out.println("- (f)eed");
            System.out.println("- (d)irect messages");
            System.out.println("- (p)rofiles");
            System.out.println("- (s)ign out");
            System.out.print("Enter choice: ");
            menuOption = s.nextLine();

            if (!menuOption.equals("s"))
            {
                processOption(menuOption);
            }
        }
        if (menuOption.equals("s")){
            System.out.println();
           user.setLoggedIn(false);
           user = null;
           start();
        }
    }

    private void processOption(String option)
    {
        if (option.equals("f"))
        {
            feed();
        }
        else if (option.equals("d"))
        {
            directMessages();
        }
        else if (option.equals("p"))
        {
            profiles();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    public void signIn(){
        System.out.println("Please enter your email");
        Scanner s = new Scanner(System.in);
        String userChoice = s.nextLine();
        String email = userChoice;
        User account = null;
        for (User user : accounts) {
            if (user.getEmail().equals(email)) {
                account = user;
            }
        }
        if (account != null){
            System.out.println("Hello " + account.getName() + "! \nPlease enter your password");
            userChoice = s.nextLine();
            String password = userChoice;
            int count = 0;
            while (!(account.getPassword().equals(password))){
                count++;
                System.out.println("Attempt #" + count);
                userChoice = s.nextLine();
                password = userChoice;
                if (count > 2){
                    System.out.println("Max attempts, you can try again later\n");
                    start();
                    break;
                }
            }
            account.setLoggedIn(true);
            run();
        }
        else {
            System.out.println("ERROR: THIS EMAIL DOES NOT EXIST \n");
            start();
        }
    }

    public void signUp(){
        String name;
        String email;
        String password;
        int age;

        String userChoice = "";
        Scanner s = new Scanner(System.in);
        boolean isTaken = false;
        System.out.print("Please enter your NAME: ");
        userChoice = s.nextLine();
        name = userChoice;
        for (User account : accounts){
            if (account.getName().equals(name)){
                isTaken = true;
                System.out.println("This username is already taken, try another one");
            }
        }
        while (isTaken){
            System.out.print("Please enter your NAME: ");
            userChoice = s.nextLine();
            name = userChoice;
            for (User account : accounts){
                if (account.getName().equals(name)){
                    isTaken = true;
                    System.out.println("This username is already taken, try another one");
                }
                else{
                    isTaken = false;
                }
            }
        }

        boolean isTakenEmail = false;
        System.out.print("Please enter your EMAIL: ");
        userChoice = s.nextLine();
        email = userChoice;
        for (User account : accounts){
            if (account.getEmail().equals(email)){
                isTakenEmail = true;
                System.out.println("This email is already taken, try another one");
            }
        }
        while (isTakenEmail){
            System.out.print("Please enter your EMAIL: ");
            userChoice = s.nextLine();
            email = userChoice;
            for (User account : accounts){
                if (account.getEmail().equals(email)){
                    isTakenEmail = true;
                    System.out.println("This email is already taken, try another one");
                }
                else{
                    isTakenEmail = false;
                }
            }
        }

        System.out.print("Please enter your PASSWORD: ");
        userChoice = s.nextLine();
        password = userChoice;
        System.out.print("Please enter your AGE: ");
        userChoice = s.nextLine();
        age = Integer.parseInt(userChoice);
        User account = new User(name, email, password, age);
        System.out.println("SUCCESSFULLY CREATED\n");
        account.setLoggedIn(true);
        accounts.add(account);
        user = account;
        run();
    }

    public void feed(){
        String feedOption = "";
        Scanner s = new Scanner(System.in);

        while (!(feedOption.equals("3"))){
            System.out.println();
            System.out.println("1) Post");
            System.out.println("2) View");
            System.out.println("3) Return menu");
            System.out.print("Enter choice: ");
            feedOption = s.nextLine();

            if (feedOption.equals("1")){
                System.out.print("Speak your mind: ");
                String post = feedOption = s.nextLine();
                activeFeed.add(post);
                user.getFeedHistory().add(post);
            }
            else if (feedOption.equals("2")){
                if (activeFeed.size() > 0){
                    System.out.println();
                    System.out.println("------------ Feed ----------");
                    for (String post : activeFeed) {
                        for (User account : accounts){
                            for (int i = 0; i < account.getFeedHistory().size(); i++){
                                if (account.getFeedHistory().get(i).equals(post)){
                                    System.out.println("- " + post + " by: " + account.getName());
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("------------ Feed ----------");
                    System.out.println("The feed is empty :/");
                }
            }
            else
            {
                System.out.println("Invalid Choice");
            }
        }
    }

    public void directMessages(){
        if (user.getFriends().size() == 0){
            System.out.println("You have no friends to message");
        }
        else
        {
            String messageOption = "";
            Scanner s = new Scanner(System.in);
            int count = 0;

            while (!(messageOption.equals(Integer.toString(count)))) {
                System.out.println();
                for (int i = 0; i < user.getFriends().size(); i++)
                {
                    String friend = user.getFriends().get(i).getName();

                    // this will print index 0 as choice 1 in the results list; better for user!
                    int choiceNum = i + 1;

                    System.out.println("" + choiceNum + ") " + friend);
                    count = choiceNum;
                }
                count += 1;
                System.out.println(count + ") Go back");
                System.out.print("Enter choice: ");
                int choice = s.nextInt();
                s.nextLine();
                messageOption = choice + "";
                if (choice != count){
                    selectedProfile = user.getFriends().get(choice-1);
                }
            }
        }
    }
    public void profiles(){
        String profileOption = "";
        Scanner s = new Scanner(System.in);

        while (!(profileOption.equals("3"))) {
            System.out.println();
            System.out.println("1) My profile");
            System.out.println("2) Search profiles");
            System.out.println("3) Return menu");
            System.out.print("Enter choice: ");
            profileOption = s.nextLine();

            if (profileOption.equals("1")) {
                while (!(profileOption.equals("7"))) {
                    System.out.println();
                    System.out.println("------------ My profile ----------");
                    System.out.println("Name: " + user.getName());
                    System.out.println("Email: " + user.getEmail() + "(HIDDEN)");
                    System.out.println("Password: " + user.getPassword() + "(HIDDEN)");
                    System.out.println("Age: " + user.getAge());
                    String Hobbies = "Hobbies: ";
                    if (user.getHobbies().size() == 0){
                        Hobbies += "You have no hobbies";
                    }
                    else
                    {
                        for (String hobby : user.getHobbies()){
                            Hobbies += hobby + ", ";
                        }
                        Hobbies = Hobbies.substring(0,Hobbies.length()-2);
                    }
                    System.out.println(Hobbies);
                    String Friends = "Friends: ";
                    if (user.getFriends().size() == 0){
                        Friends += "You have no friends";
                    }
                    else
                    {
                        for (User friend : user.getFriends()){
                            Friends += friend.getName() + ", ";
                        }
                        Friends = Friends.substring(0,Friends.length()-2);
                    }
                    System.out.println(Friends);
                    System.out.println();
                    System.out.println("1) Change Name");
                    System.out.println("2) Change Email");
                    System.out.println("3) Change Password");
                    System.out.println("4) Change Age");
                    System.out.println("5) Edit Hobbies");
                    System.out.println("6) Friend requests");
                    System.out.println("7) Go Back");
                    System.out.print("Enter choice: ");
                    profileOption = s.nextLine();

                    if (profileOption.equals("1")){
                        System.out.print("Enter new name: ");
                        profileOption = s.nextLine();
                        user.setName(profileOption);
                        System.out.println("Name has been updated");
                    }
                    else if (profileOption.equals("2")){
                        System.out.print("Enter new email: ");
                        profileOption = s.nextLine();
                        user.setEmail(profileOption);
                        System.out.println("Email has been updated");
                    }
                    else if (profileOption.equals("3")){
                        System.out.print("Enter new password: ");
                        profileOption = s.nextLine();
                        user.setPassword(profileOption);
                        System.out.println("Password has been updated");
                    }
                    else if (profileOption.equals("4")){
                        System.out.print("Enter new age: ");
                        profileOption = s.nextLine();
                        user.setAge(Integer.parseInt(profileOption));
                        System.out.println("Age has been updated");
                    }
                    else if (profileOption.equals("5")){
                        while (!(profileOption.equals("3"))) {
                            System.out.println();
                            System.out.println("1) Add");
                            System.out.println("2) Remove");
                            System.out.println("3) Go back");
                            System.out.print("Enter choice: ");
                            profileOption = s.nextLine();

                            if (profileOption.equals("1")){
                                System.out.print("Enter your new hobby: ");
                                profileOption = s.nextLine();
                                user.getHobbies().add(profileOption);
                            }
                            else if (profileOption.equals("2")){
                                System.out.println();
                                System.out.println("------------ My Hobbies ----------");
                                for (int i = 0; i < user.getHobbies().size(); i++)
                                {
                                    String hobby = user.getHobbies().get(i);

                                    // this will print index 0 as choice 1 in the results list; better for user!
                                    int choiceNum = i + 1;

                                    System.out.println("" + choiceNum + ") " + hobby);
                                }
                                System.out.print("Enter choice: ");
                                int choice = s.nextInt();
                                s.nextLine();

                                user.getHobbies().remove(choice-1);
                                System.out.println("The hobby has been removed");
                            }
                            else
                            {
                                System.out.println("Invalid Choice");
                            }
                        }
                    }
                    else if (profileOption.equals("6")){
                        System.out.println();
                        System.out.println("------------ Incoming requests ----------");
                        if (user.getIncomingRequests().size() == 0){
                            System.out.println("You have no incoming requests");
                        }
                        else
                        {
                            for (int i = 0; i < user.getIncomingRequests().size(); i++)
                            {
                                String name = user.getIncomingRequests().get(i).getName();

                                // this will print index 0 as choice 1 in the results list; better for user!
                                int choiceNum = i + 1;

                                System.out.println("" + choiceNum + ") " + name);
                            }
                            System.out.print("Enter choice: ");
                            int choice = s.nextInt();
                            s.nextLine();

                            if (!(choice == user.getIncomingRequests().size()+1)){
                                selectedProfile = user.getIncomingRequests().get(choice-1);
                                System.out.println();
                                System.out.println("1) Accept");
                                System.out.println("2) Decline");
                                System.out.print("Enter choice: ");
                                profileOption = s.nextLine();
                                if (profileOption.equals("1")){
                                    System.out.println("You have added " + selectedProfile.getName() + " to your list");
                                    user.getFriends().add(selectedProfile);
                                    selectedProfile.getFriends().add(user);
                                    selectedProfile.getOutgoingRequests().remove(user);
                                    user.getIncomingRequests().remove(selectedProfile);
                                } else if (profileOption.equals("2")){
                                    System.out.println("You have declined " + selectedProfile.getName());
                                    selectedProfile.getOutgoingRequests().remove(user);
                                    user.getIncomingRequests().remove(selectedProfile);
                                }
                            }
                            else
                            {
                                System.out.println("Invalid Choice");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Invalid Choice");
                    }
                }
            }
            else if (profileOption.equals("2")){
                System.out.print("Type in the username: ");
                profileOption = s.nextLine();
                ArrayList<User> searchedProfiles = new ArrayList<User>();

                for (int i = 0; i < accounts.size(); i++)
                {
                    String profileName = accounts.get(i).getName();
                    profileName = profileName.toLowerCase();

                    if (profileName.contains(profileOption))
                    {
                        searchedProfiles.add(accounts.get(i));
                    }
                }

                if (searchedProfiles.size() == 0){
                    System.out.println("Error: Users not found");
                }
                else
                {
                    for (int i = 0; i < searchedProfiles.size(); i++)
                    {
                        String profileName = searchedProfiles.get(i).getName();
                        int choiceNum = i + 1;
                        System.out.println("" + choiceNum + ". " + profileName);
                    }
                    System.out.println("Which profile would you like to view");
                    System.out.print("Enter number: ");
                    int choice = s.nextInt();
                    s.nextLine();
                    selectedProfile = searchedProfiles.get(choice-1);
                }

                if (selectedProfile != null){
                    System.out.println();
                    selectedProfile.displayInfo();
                    boolean added = false;
                    boolean sent = false;
                    while (!(profileOption.equals("2"))) {
                        System.out.println();
                        System.out.println("1) Send a friend request");
                        System.out.println("2) Go back");
                        System.out.print("Enter choice: ");
                        profileOption = s.nextLine();
                        if (profileOption.equals("1")){
                            if (selectedProfile.getName().equals(user.getName())){
                                System.out.println("You cannot add yourself");
                            }
                            else
                            {
                                for (User friend : user.getFriends()){
                                    if (friend.getName().equals(selectedProfile.getName())){
                                        added = true;
                                        break;
                                    }
                                }
                                if (sent == true && added == false){
                                    System.out.println("You've already sent a request");
                                }
                                else if (!added){
                                    sent = true;
                                    System.out.println("You have sent a friend request to " + selectedProfile.getName());
                                    user.getOutgoingRequests().add(selectedProfile);
                                    selectedProfile.getIncomingRequests().add(user);
                                }
                                else {
                                    System.out.println("You're already friends with " + selectedProfile.getName());
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Invalid Choice");
                        }
                    }
                }
            }
            else
            {
                System.out.println("Invalid Choice");
            }
        }
    }
}
