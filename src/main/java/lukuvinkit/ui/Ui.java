package lukuvinkit.ui;

import java.util.ArrayList;
import lukuvinkit.domain.Blogpost;
import lukuvinkit.domain.Kirja;
import lukuvinkit.domain.Lukuvinkki;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.domain.Video;
import lukuvinkit.io.IO;

public class Ui {

  private IO io;
  private LukuvinkkienKasittely kasittely;
  private boolean applicationOn;
  private String typeToAdd;

  public Ui(IO io, LukuvinkkienKasittely kasittely) {
    this.io = io;
    this.kasittely = kasittely;
    this.applicationOn = true;
    this.typeToAdd = "";
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
    io.print("3. Poista lukuvinkki");
    io.print("4. Sulje ohjelma");
    io.print("\nKomento: ");

    String command = io.nextCommand();
    if (command.equals("1")) {
      chooseRecommendationType();
    }
    if (command.equals("2")) {
      listRecommendations();
    }
    if (command.equals("3")) {
      removeRecommendation();
    }
    if (command.equals("4")) {
      shutDown();
    }
  }

  public void chooseRecommendationType() {
    io.print("Lisää: ");
    io.print("1. Kirja");
    io.print("2. Video");
    io.print("3. Blogpost");
    io.print("4. Podcast");
    io.print("\nKomento: ");

    String command = io.nextCommand();
    if (command.equals("1")) {
      typeToAdd = "Kirja";
      addRecommendation();
    }
    if (command.equals("2")) {
      typeToAdd = "Video";
      addRecommendation();
    }
    if (command.equals("3")) {
      typeToAdd = "Blogpost";
      addRecommendation();
    }
    if (command.equals("4")) {
      typeToAdd = "Podcast";
      addRecommendation();
    }
  }

  public String addUrl() {
    while (true) {
      io.print("\nUrl: ");
      String url = io.nextCommand();
      if (url.equals("")) {
        io.print("url ei voi olla tyhjä!");
      } else {
        return url;
      }
    }
  }

  public String addWriter() {
    while (true) {
      io.print("\nKirjoittaja: ");
      String writer = io.nextCommand();
      if (writer.equals("")) {
        io.print("kirjoittaja ei voi olla tyhjä!");
      } else {
        return writer;
      }
    }
  }

  public String addDescription() {
    return "";
  }

  public void addRecommendation() {
    while (true) {
      io.print("\nLisää lukuvinkki");
      io.print("\nOtsikko: ");

      String title = io.nextCommand();
      if (title.equals("")) {
        io.print("Otsikko ei voi olla tyhjä!");
        continue;
      }
      if (typeToAdd.equals("Kirja")) {
        String writer = addWriter();
        Kirja kirja = new Kirja(title, writer);
        saveToDatabase(kirja);
        break;
      }
      if (typeToAdd.equals("Video")) {
        String url = addUrl();
        Video video = new Video(title, url);
        saveToDatabase(video);
        break;
      }
      if (typeToAdd.equals("Blogpost")) {
        String url = addUrl();
        Blogpost blog = new Blogpost(title, url);
        saveToDatabase(blog);
      }
      if (typeToAdd.equals("Podcast")) {
        // podcastin nimi pakollinen
        // pakollinen kuvaus
      }
    }
    mainOptions();
  }

  public void saveToDatabase(Lukuvinkki lukuvinkki) {
    kasittely.saveRecommendation(lukuvinkki);
    io.print(lukuvinkki.getOtsikko() + " lisätty");
    io.print("\n");
  }

  public void listRecommendations() {
    io.print("\nTallennetut lukuvinkit: ");
    ArrayList<Lukuvinkki> recommendations = kasittely.getAllRecommendations();
    for (int i = 0; i < recommendations.size(); i++) {
      io.print(i + 1 + ". " + recommendations.get(i).getOtsikko());
    }
    io.print("\n");
  }

  public void removeRecommendation() {
    ArrayList<Lukuvinkki> recommendations = kasittely.getAllRecommendations();
    int count = recommendations.size();
    if (count == 0) {
      io.print("\nEi tallennettuja lukuvinkkejä");
      io.print("\n");
      return;
    }
    while (true) {
      io.print("\nPoistettavan lukuvinkin indeksi: ");
      String input = io.nextCommand();
      try {
        int index = Integer.parseInt(input);
        if (index < 1 || index > count) {
          io.print("Indeksin täytyy olla välillä 1-" + count);
          continue;
        }
        Lukuvinkki l = recommendations.get(index - 1);
        if (confirm("Haluatko varmasti poistaa lukuvinkin \"" + l.getOtsikko() + "\"?")) {
          kasittely.deleteRecommendation(l);
          io.print("Lukuvinkki \"" + l.getOtsikko() + "\" poistettu");
        }
        break;
      } catch (NumberFormatException e) {
        io.print("Indeksin täytyy olla kokonaisluku");
      }
    }
    io.print("\n");
  }

  private boolean confirm(String confirmationMessage) {
    while (true) {
      io.print(confirmationMessage + " [y/n]");
      String input = io.nextCommand();
      if (input.equals("y")) {
        return true;
      }
      if (input.equals("n")) {
        return false;
      }
    }
  }

  public void shutDown() {
    applicationOn = false;
  }
}
