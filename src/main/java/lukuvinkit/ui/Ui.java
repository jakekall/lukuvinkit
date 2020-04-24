package lukuvinkit.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lukuvinkit.domain.Blogpost;
import lukuvinkit.domain.Kirja;
import lukuvinkit.domain.Lukuvinkki;
import lukuvinkit.domain.LukuvinkkiTyyppi;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.domain.Podcast;
import lukuvinkit.domain.Video;
import lukuvinkit.io.IO;
import lukuvinkit.util.TagParser;

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

  public void startUi() throws SQLException {
    while (applicationOn) {
      mainOptions();
    }
  }

  public void mainOptions() throws SQLException {
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
      chooseHowToListRecommendations();
    }
    if (command.equals("3")) {
      removeRecommendation();
    }
    if (command.equals("4")) {
      shutDown();
    }
  }

  public void chooseHowToListRecommendations() throws SQLException {
    io.print("1. Listaa kaikki lukuvinkit");
    io.print("2. Listaa tagin perusteella");
    io.print("3. Listaa tyypin perusteella");
    io.print("\nKomento: ");
    String command = io.nextCommand();

    if (command.equals("1")) {
      listAllRecommendations();
    } else if (command.equals("2")) {
      io.print("Haettava tagi: ");
      command = io.nextCommand();
      listRecommendationsByTag(command);
    } else if (command.equals("3")) {
      io.print("Valitse haettava tyyppi: ");
      io.print("1. Kirja");
      io.print("2. Video");
      io.print("3. Blogpost");
      io.print("4. Podcast");
      io.print("\nKomento: ");
      command = io.nextCommand();
      listRecommendationsByType(command);
    }
  }

  public void chooseRecommendationType() throws SQLException {
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

  public List<String> addTags() {
    io.print("\nTagit (erottele pilkulla): ");
    String tags = io.nextCommand();
    return TagParser.stringToList(tags);
  }

  public String addDescription() {
    while (true) {
      io.print("\nKuvaus: ");
      String description = io.nextCommand();
      if (typeToAdd.equals("Podcast") && description.isEmpty()) {
        io.print("kuvaus ei voi olla tyhjä!");
      } else {
        return description;
      }
    }
  }

  public void addRecommendation() throws SQLException {
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
        String description = addDescription();
        List<String> tags = addTags();
        Kirja kirja = new Kirja(title, writer, description, tags);
        saveToDatabase(kirja);
        break;
      }
      if (typeToAdd.equals("Video")) {
        String url = addUrl();
        String description = addDescription();
        List<String> tags = addTags();
        Video video = new Video(title, url, description, tags);
        saveToDatabase(video);
        break;
      }
      if (typeToAdd.equals("Blogpost")) {
        String url = addUrl();
        String description = addDescription();
        List<String> tags = addTags();
        Blogpost blog = new Blogpost(title, url, description, tags);
        saveToDatabase(blog);
        break;
      }
      if (typeToAdd.equals("Podcast")) {
        String url = addUrl();
        String description = addDescription();
        List<String> tags = addTags();
        Podcast podcast = new Podcast(title, url, description, tags);
        saveToDatabase(podcast);
        break;
      }
    }
    mainOptions();
  }

  public void saveToDatabase(Lukuvinkki lukuvinkki) throws SQLException {
    kasittely.saveRecommendation(lukuvinkki);
    io.print(lukuvinkki.getOtsikko() + " lisätty");
    io.print("\n");
  }

  public void listAllRecommendations() throws SQLException {
    List<Lukuvinkki> recommendations = kasittely.getAllRecommendations();
    io.print("\nTallennetut lukuvinkit: ");
    formRecommendationList(recommendations);
  }

  public void listRecommendationsByTag(String tag) throws SQLException {
    List<Lukuvinkki> recommendations = kasittely.getRecommendationsByTag(tag);
    io.print("\nTallennetut lukuvinkit: ");
    formRecommendationList(recommendations);
  }

  public void listRecommendationsByType(String command) throws SQLException {
    List<Lukuvinkki> recommendations = new ArrayList<>();
    if (command.equals("1")) {
      recommendations.addAll(kasittely.getRecommendationsByType(LukuvinkkiTyyppi.KIRJA));
    } else if (command.equals("2")) {
      recommendations.addAll(kasittely.getRecommendationsByType(LukuvinkkiTyyppi.VIDEO));
    } else if (command.equals("3")) {
      recommendations.addAll(kasittely.getRecommendationsByType(LukuvinkkiTyyppi.BLOGPOST));
    } else if (command.equals("4")) {
      recommendations.addAll(kasittely.getRecommendationsByType(LukuvinkkiTyyppi.PODCAST));
    }
    io.print("\nTallennetut lukuvinkit: ");
    formRecommendationList(recommendations);
  }

  public void formRecommendationList(List<Lukuvinkki> recommendations) {
    if (recommendations.isEmpty()) {
      io.print("Lukuvinkkejä ei löytynyt");
      io.print("\n");
    } else {
      for (int i = 0; i < recommendations.size(); i++) {
        Lukuvinkki l = recommendations.get(i);
        io.print("Id: " + l.getId());
        io.print("Otsikko: " + l.getOtsikko());
        io.print("Tyyppi: " + l.getTyyppi());
        LukuvinkkiTyyppi tyyppi = l.getTyyppi();
        if (tyyppi.equals(LukuvinkkiTyyppi.VIDEO)) {
          printVideoUrl(l);
        }
        if (tyyppi.equals(LukuvinkkiTyyppi.BLOGPOST)) {
          printBlogpostUrl(l);
        }
        if (tyyppi.equals(LukuvinkkiTyyppi.KIRJA)) {
          printAuthor(l);
        }
        if (!l.getKuvaus().isEmpty()) {
          io.print("Kuvaus: " + l.getKuvaus());
        }
        if (!l.getTags().isEmpty()) {
          io.print("Tagit: " + l.getTags());
        }
        io.print("\n");
      }
    }
  }

  public void printAuthor(Lukuvinkki l) {
    Kirja kirja = (Kirja) l;
    io.print("Kirjailija: " + kirja.getKirjailija());
  }

  public void printVideoUrl(Lukuvinkki l) {
    Video video = (Video) l;
    io.print("Url: " + video.getUrl());
  }

  public void printBlogpostUrl(Lukuvinkki l) {
    Blogpost blog = (Blogpost) l;
    io.print("Url: " + blog.getUrl());
  }

  public void removeRecommendation() throws SQLException {
    ArrayList<Lukuvinkki> recommendations = kasittely.getAllRecommendations();
    int count = recommendations.size();
    if (count == 0) {
      io.print("\nEi tallennettuja lukuvinkkejä");
      io.print("\n");
      return;
    }
    while (true) {
      io.print("\nPoistettavan lukuvinkin id: ");
      String input = io.nextCommand();
      try {
        int id = Integer.parseInt(input);
        Optional<Lukuvinkki> optional = recommendations.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
        if (!optional.isPresent()) {
          io.print("Lukuvinkkiä ei löytynyt id:llä " + id);
          continue;
        }
        Lukuvinkki l = optional.get();
        if (confirm("Haluatko varmasti poistaa lukuvinkin \"" + l.getOtsikko() + "\"?")) {
          kasittely.deleteRecommendation(l);
          io.print("Lukuvinkki \"" + l.getOtsikko() + "\" poistettu");
        }
        break;
      } catch (NumberFormatException e) {
        io.print("Id:n täytyy olla kokonaisluku");
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
