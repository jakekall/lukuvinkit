public class Ui {
  private IO io;
  private LukuvinkkienKasittely kasittely;
  private boolean applicationOn;

  public Ui(IO io, LukuvinkkienKasittely kasittely) {
    this.io = io;
    this.kasittely = kasittely;
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
        kasittely.saveRecommendation(new Lukuvinkki(title));
        io.print(title + " lisätty!");
        io.print("\n");
        break;
      }
    }
    mainOptions();
  }

  public void listRecommendations() {
    io.print("\nTallennetut lukuvinkit: ");
    for (Lukuvinkki l : kasittely.getAllRecommendations()) {
      io.print(l.getOtsikko());
    }
    io.print("\n");
  }

  public void shutDown() {
    applicationOn = false;
  }
}
