import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class Saver {
    public static void writeToFile(ArrayList<User> accounts, ArrayList<String> activeFeed){
        try {
            FileWriter myWriter = new FileWriter("src/Database");
            for (User account : accounts){
                String userData = "User|" + account.getName() + "|" + account.getEmail() + "|" + account.getPassword() + "|" + account.getAge() + "|";
                String hobbies = "";
                for (String hobby : account.getHobbies()){
                    hobbies += hobby + ",";
                }
                if (hobbies.length() > 0){
                    hobbies = hobbies.substring(0, hobbies.length()-1);
                }

                String friends = "";
                for (User friend : account.getFriends()){
                    friends += friend.getName() + ",";
                }
                if (friends.length() > 0){
                    friends = friends.substring(0, friends.length()-1);
                }

                String comment = "";
                for (String feed : account.getFeedHistory()){
                    comment += feed + "/";
                }
                if (comment.length() > 0){
                    comment = comment.substring(0, comment.length()-1);
                }

                String userText = "";
                for (String privateMessage : account.getTextHistory()){
                    userText += privateMessage + ",";
                }
                if (userText.length() > 0){
                    userText = userText.substring(0, userText.length()-1);
                }

                if (hobbies.length() < 1){
                    hobbies = "null";
                }
                if (friends.length() < 1){
                    friends = "null";
                }
                if (comment.length() < 1){
                    comment = "null";
                }
                if (userText.length() < 1){
                    userText = "null";
                }
                userData += hobbies + "|" + friends + "|" + comment + "|" + userText + "\n";
                myWriter.write(userData);
            }

            for (String feedComment : activeFeed){
                String feed = "Feed|" + feedComment + "\n";
                myWriter.write(feed);
            }

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
