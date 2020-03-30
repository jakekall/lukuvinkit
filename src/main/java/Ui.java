import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private ArrayList<Lukuvinkki> list;
    private boolean applicationOn;

    public Ui(Scanner scanner, ArrayList<Lukuvinkki> list) {
        this.scanner = scanner;
        this.list = list;
        this.applicationOn = true;
    }
    public void startUi() {
        while (applicationOn) {
            mainOptions();
        }
    }
    public void mainOptions() {
        System.out.println("Komennot: ");
        System.out.println("1. Lisää lukuvinkki");
        System.out.println("2. Listaa lukuvinkit");
        System.out.println("3. Sulje ohjelma");
        System.out.println("\nKomento: ");

        String command = scanner.nextLine();
            if (command.equals("1")) {
                addRecommendation();
            }
            if (command.equals("2")) {
                listRecommendations();
            }
            if (command.equals("3")) {
                shutDown();
            }
    }
    public void addRecommendation() {
        while (true) {
            System.out.println("\nLisää lukuvinkki");
            System.out.println("\nOtsikko: ");

            String title = scanner.nextLine();
            if (title.equals("")) {
                System.out.println("Otsikko ei voi olla tyhjä!");
                continue;
            } else {
                System.out.println(title + " lisätty!");
                System.out.println();
                list.add(new Lukuvinkki(title));
                break;
            }
        }
        mainOptions();

    }
    public void listRecommendations() {
        System.out.println("\nTallennetut lukuvinkit: ");
        for (Lukuvinkki l : list) {
            System.out.println(l.getOtsikko());
        }
        System.out.println();
    }

    public void shutDown() {
        applicationOn = false;
    }


}
