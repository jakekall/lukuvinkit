package lukuvinkit.domain;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.SQLException;
import lukuvinkit.Database;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;
import org.junit.Before;
import org.junit.Test;

public class LukuvinkkienKasittelyTest {

  private Database testDatabase;

  private BlogpostDao blogpostDao;
  private KirjaDao kirjaDao;
  private PodcastDao podcastDao;
  private VideoDao videoDao;

  private LukuvinkkienKasittely lukuvinkkienKasittely;

  @Before
  public void setup() {
    File dbFile = new File("test.db");

    if (dbFile.exists()) {
      dbFile.delete();
    }

    testDatabase = new Database("jdbc:sqlite:test.db");

    blogpostDao = new BlogpostDao(testDatabase);
    kirjaDao = new KirjaDao(testDatabase);
    podcastDao = new PodcastDao(testDatabase);
    videoDao = new VideoDao(testDatabase);

    lukuvinkkienKasittely = new LukuvinkkienKasittely(blogpostDao, kirjaDao, podcastDao, videoDao);
  }

  @Test
  public void lukuvinkkienKasittelySavesKirjaLukuvinkkiOtsikko() throws SQLException {
    Kirja kirja = new Kirja("Clean Code", "Robert Martin");

    lukuvinkkienKasittely.saveRecommendation(kirja);

    assertEquals(lukuvinkkienKasittely.getAllRecommendations().get(0).getOtsikko(),
        kirja.getOtsikko());
  }

  @Test
  public void lukuvinkkienKasittelySavesKirjaLukuvinkkiKirjailija() throws SQLException {
    Kirja kirja = new Kirja("Clean Code", "Robert Martin");

    lukuvinkkienKasittely.saveRecommendation(kirja);

    Kirja savedKirja = (Kirja) lukuvinkkienKasittely.getAllRecommendations().get(0);

    assertEquals(savedKirja.getKirjailija(), kirja.getKirjailija());
  }

  @Test
  public void lukuvinkkienKasittelyReturnsAllAddedLukuvinkit() {
//    LukuvinkkienKasittely lukuvinkkienKasittely = new LukuvinkkienKasittely();
//
//    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
//    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
//    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
//    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
//
//    assertEquals(4, lukuvinkkienKasittely.getAllRecommendations().size());
  }
}
