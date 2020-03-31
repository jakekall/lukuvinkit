import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Lukuvinkki> list = new ArrayList<>();
        Ui ui = new Ui(new KonsoliIO(), list);
        ui.startUi();
    }
}

