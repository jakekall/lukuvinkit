import java.util.ArrayList;

public class Ui {
    private IO io;
    private ArrayList<Lukuvinkki> list;
    private boolean applicationOn;

    public Ui(IO io, ArrayList<Lukuvinkki> list) {
        this.io = io;
        this.list = list;
        this.applicationOn = true;
    }
    public void startUi() {
        while (applicationOn) {
            mainOptions();
        }
    }
    public void mainOptions() {
        io.print("Komennot: ");
        io.print("1. Lisää lukuvinkki");
        io.print("2. Listaa lukuvinkit");
        io.print("3. Sulje ohjelma");
        io.print("\nKomento: ");

        String command = io.nextCommand();
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
            io.print("\nLisää lukuvinkki");
            io.print("\nOtsikko: ");

            String title = io.nextCommand();
            if (title.equals("")) {
                io.print("Otsikko ei voi olla tyhjä!");
                continue;
            } else {
                io.print(title + " lisätty!");
                io.print("\n");
                list.add(new Lukuvinkki(title));
                break;
            }
        }
        mainOptions();

    }
    public void listRecommendations() {
        io.print("\nTallennetut lukuvinkit: ");
        for (Lukuvinkki l : list) {
            io.print(l.getOtsikko());
        }
        io.print("\n");
    }

    public void shutDown() {
        applicationOn = false;
    }


}
