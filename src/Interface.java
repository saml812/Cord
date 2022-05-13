import com.sun.net.httpserver.Authenticator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Interface {
    ArrayList<User> accounts = new ArrayList<User>();
    ArrayList<String> activeFeed = new ArrayList<String>();
    User user = null;
    public void start(){

        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to Cord!");
        System.out.println("1) Sign in");
        System.out.println("2) Sign up");
        String userChoice = s.nextLine();

        if (userChoice.equals("1")){
            signIn();
        }
        if (userChoice.equals("2")){
            signUp();
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

        }
        else if (option.equals("p"))
        {

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

        Scanner s = new Scanner(System.in);
        System.out.print("Please enter your NAME: ");
        String userChoice = s.nextLine();
        name = userChoice;
        System.out.print("Please enter your EMAIL: ");
        userChoice = s.nextLine();
        email = userChoice;
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
        System.out.println("Welcome to the feed");
        Scanner s = new Scanner(System.in);
        System.out.println("1) Post");
        System.out.println("2) View");
        System.out.println("3) Return menu");
        String userChoice = s.nextLine();

        while (!(userChoice.equals("3"))){
            System.out.println("1) Post");
            System.out.println("2) View");
            System.out.println("3) Return menu");
            userChoice = s.nextLine();

            if (userChoice.equals("1")){
                System.out.println("Speak your mind: ");
                String post = userChoice = s.nextLine();
                activeFeed.add(post);
                user.getFeedHistory().add(post);
            }
            if (userChoice.equals("2")){
                if (activeFeed == null){
                    System.out.println("The feed is empty :/");
                }
                else
                {
                    for (String post : activeFeed) {
                        System.out.println("- " + post + " by: " + user.getName());
                    }
                }
            }
        }

    }
}
