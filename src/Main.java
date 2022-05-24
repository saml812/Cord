import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        Data server = new Data();
        server.loadData();

        new LoginPage(server);

        //Interface Cord = new Interface();
        //Cord.loadData();
        //Cord.start();
    }
}
