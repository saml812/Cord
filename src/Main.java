import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        ServerData server = new ServerData();
        server.loadData();

        new LoginPage(server);

        //Interface Cord = new Interface();
        //Cord.loadData();
        //Cord.start();
    }
}
