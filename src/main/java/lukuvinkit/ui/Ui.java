package lukuvinkit.ui;

import lukuvinkit.domain.Lukuvinkki;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.IO;

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

  public void chooseRecommendationType() {
    io.print("Lisää: ");
    io.print("1. Kirja");
    io.print("2. Video");
    io.print("3. Blogi");
    io.print("4. Podcast");
    io.print("\nKomento: ");

    String command = io.nextCommand();
    if (command.equals("1")) {
      addBook();
    }
    if (command.equals("2")) {

    }
    if (command.equals("3")) {

    }
    if (command.equals("4")) {

    }
  }

  public void addBook() {
    while(true) {
      io.print("\nLisää kirja");
      io.print("\nKirjoittaja: ");

      String writer = io.nextCommand();
      if (writer.equals("")) {
        io.print("Kirjalle täytyy lisätä kirjoittaja!");
        continue;
      }
      io.print("\nKirjan nimi: ");
      String book = io.nextCommand();
      if (writer.equals("")) {
        io.print("Kirjlla täytyy lisätä nimi!");
        continue;
      }
    }
  }

  public void addRecommendation() {
    while (true) {
      io.print("\nLisää lukuvinkki");
      io.print("\nOtsikko: ");

      String title = io.nextCommand();
      if (title.equals("")) {
        io.print("Otsikko ei voi olla tyhjä!");
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
