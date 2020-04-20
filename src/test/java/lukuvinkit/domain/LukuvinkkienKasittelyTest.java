package lukuvinkit.domain;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.LukuvinkkiDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;
import lukuvinkit.db.TestDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LukuvinkkienKasittelyTest {

  private TestDatabase testDatabase;
  private BlogpostDao blogpostDao;
  private KirjaDao kirjaDao;
  private PodcastDao podcastDao;
  private VideoDao videoDao;

  private LukuvinkkienKasittely lukuvinkkienKasittely;

  @Before
  public void setup() throws SQLException {
    testDatabase = new TestDatabase();

    LukuvinkkiDao lukuvinkkiDao = new LukuvinkkiDao(testDatabase);
    blogpostDao = new BlogpostDao(testDatabase, lukuvinkkiDao);
    kirjaDao = new KirjaDao(testDatabase, lukuvinkkiDao);
    podcastDao = new PodcastDao(testDatabase, lukuvinkkiDao);
    videoDao = new VideoDao(testDatabase, lukuvinkkiDao);

    lukuvinkkienKasittely = new LukuvinkkienKasittely(blogpostDao, kirjaDao, podcastDao, videoDao);
  }

  @After
  public void cleanup() throws SQLException {
    testDatabase.close();
  }

  @Test
  public void lukuvinkkienKasittelySavesKirjaLukuvinkkiOtsikko() throws SQLException {
    Kirja kirja = new Kirja("Clean Code", "Robert Martin", new ArrayList<>());

    lukuvinkkienKasittely.saveRecommendation(kirja);

    assertEquals(lukuvinkkienKasittely.getAllRecommendations().get(0).getOtsikko(),
            kirja.getOtsikko());
  }

  @Test
  public void lukuvinkkienKasittelySavesKirjaLukuvinkkiKirjailija() throws SQLException {
    Kirja kirja = new Kirja("Clean Code", "Robert Martin", new ArrayList<>());

    lukuvinkkienKasittely.saveRecommendation(kirja);

    Kirja savedKirja = (Kirja) lukuvinkkienKasittely.getAllRecommendations().get(0);

    assertEquals(savedKirja.getKirjailija(), kirja.getKirjailija());
  }

}
