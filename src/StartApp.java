import java.io.FileNotFoundException;

public class StartApp {
    public static void main(String[] args) throws FileNotFoundException {

        AppData app = new AppData();
        app.loadData();
        new LoginPage(app);
    }
}
