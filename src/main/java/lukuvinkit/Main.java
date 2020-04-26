package lukuvinkit;

import java.util.Scanner;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.LukuvinkkiDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;
import lukuvinkit.db.Database;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.KonsoliIO;
import lukuvinkit.ui.Ui;

public class Main {

  public static void main(String[] args) throws Exception {
    String databaseUrl = "jdbc:sqlite:database.db";

    Database db = new Database(databaseUrl);

    LukuvinkkiDao lukuvinkkiDao = new LukuvinkkiDao(db);    
    BlogpostDao blogpostDao = new BlogpostDao(db, lukuvinkkiDao);
    KirjaDao kirjaDao = new KirjaDao(db, lukuvinkkiDao);
    PodcastDao podcastDao = new PodcastDao(db, lukuvinkkiDao);
    VideoDao videoDao = new VideoDao(db, lukuvinkkiDao);

    Scanner scanner = new Scanner(System.in);

    Ui ui = new Ui(new KonsoliIO(scanner),
        new LukuvinkkienKasittely(lukuvinkkiDao, blogpostDao, kirjaDao, podcastDao, videoDao));

    ui.startUi();
  }

}
