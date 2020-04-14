package lukuvinkit;

import lukuvinkit.db.Database;
import java.util.Scanner;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.KonsoliIO;
import lukuvinkit.ui.Ui;

public class Main {

  public static void main(String[] args) throws Exception {
    String databaseUrl = "jdbc:sqlite:database.db";

    Database db = new Database(databaseUrl);

    BlogpostDao blogpostDao = new BlogpostDao(db);
    KirjaDao kirjaDao = new KirjaDao(db);
    PodcastDao podcastDao = new PodcastDao(db);
    VideoDao videoDao = new VideoDao(db);

    Scanner scanner = new Scanner(System.in);

    Ui ui = new Ui(new KonsoliIO(scanner),
        new LukuvinkkienKasittely(blogpostDao, kirjaDao, podcastDao, videoDao));

    ui.startUi();
  }

}
