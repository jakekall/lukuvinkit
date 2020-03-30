import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Lukuvinkki> list = new ArrayList<>();
        Ui ui = new Ui(scanner, list);
        ui.startUi();
    }
}

