import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

public class AppSaver {
    public static void writeToFile(ArrayList<User> accounts, ArrayList<String> activeFeed){
        try {
            FileWriter myWriter = new FileWriter("src/Database");
            ArrayList<String> needAdded = new ArrayList<String>();
            for (User account : accounts){
                String needAdd = "";
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
                    userText += privateMessage + "\\";
                }
                if (userText.length() > 0){
                    userText = userText.substring(0, userText.length()-1);
                }

                String incomingRequests = "";
                for (User friend : account.getIncomingRequests()){
                    incomingRequests += friend.getName() + ",";
                }
                if (incomingRequests.length() > 0){
                    incomingRequests = incomingRequests.substring(0, incomingRequests.length()-1);
                }

                String outgoingRequests = "";
                for (User friend : account.getOutgoingRequests()){
                    outgoingRequests += friend.getName() + ",";
                }
                if (outgoingRequests.length() > 0){
                    outgoingRequests = outgoingRequests.substring(0, outgoingRequests.length()-1);
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
                if (incomingRequests.length() < 1){
                    incomingRequests = "null";
                }
                if (outgoingRequests.length() < 1){
                    outgoingRequests = "null";
                }
                userData += hobbies + "|" + friends + "|" + comment + "|" + userText + "|" + incomingRequests + "|" + outgoingRequests + "\n";
                needAdd = "Add|" + account.getName() + "|" + friends + "|" + incomingRequests + "|" + outgoingRequests;
                needAdded.add(needAdd);
                myWriter.write(userData);
            }

            for (String feedComment : activeFeed){
                String feed = "Feed|" + feedComment + "\n";
                myWriter.write(feed);
            }

            for (String text : needAdded){
                myWriter.write(text + "\n");
            }
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
