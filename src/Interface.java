import com.sun.net.httpserver.Authenticator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Interface {
    ArrayList<User> accounts = new ArrayList<User>();
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
        System.out.println("Welcome " + user.getName() + "! The greatest media platform ever created!");
        Scanner s = new Scanner(System.in);
    }

    private void processOption(String option)
    {
        if (option.equals("1"))
        {

        }
        else if (option.equals("2"))
        {

        }
        else if (option.equals("3"))
        {

        }
        else if (option.equals("4"))
        {

        }
        else if (option.equals("5"))
        {

        }
        else if (option.equals("6"))
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
            System.out.println("Hello " + account.getName() + "! \n Please enter your password");
            userChoice = s.nextLine();
            String password = userChoice;
            while (account.getPassword().equals(password)){
                int count = 0;
                count++;
                System.out.println("Attempt #" + count);
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
        System.out.println("Please enter your NAME");
        String userChoice = s.nextLine();
        name = userChoice;
        System.out.println("Please enter your EMAIL");
        userChoice = s.nextLine();
        email = userChoice;
        System.out.println("Please enter your PASSWORD");
        userChoice = s.nextLine();
        password = userChoice;
        System.out.println("Please enter your AGE");
        userChoice = s.nextLine();
        age = Integer.parseInt(userChoice);
        User account = new User(name, email, password, age, null, null ,null, null);
        System.out.println("SUCCESSFULLY CREATED");
        account.setLoggedIn(true);
        accounts.add(account);
        run();
    }
}
