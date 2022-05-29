import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        AppData app = new AppData();
        app.loadData();
        new LoginPage(app);
    }
}
