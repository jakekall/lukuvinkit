package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Podcast;
import org.junit.Before;
import org.junit.Test;

public class PodcastDaoTest {

  private Database db;
  private PodcastDao podcastDao;

  @Before
  public void setup() {
    File dbFile = new File("test.db");
    if (dbFile.exists()) {
      dbFile.delete();
    }

    db = new Database("jdbc:sqlite:test.db");
    podcastDao = new PodcastDao(db);
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Podcast kirja = new Podcast("Test Podcast 1", "www.example.com", "Podcastin kuvaus");
    int id = podcastDao.create(kirja);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Podcast podcast1 = new Podcast("Test Podcast 1", "www.example.com", "Ekan podcastin kuvaus");
    Podcast podcast2 = new Podcast("Test Podcast 2", "www.example.com", "Tokan podcastin kuvaus");
    int podcast1Id = podcastDao.create(podcast1);
    int podcast2Id = podcastDao.create(podcast2);

    podcast1.setId(podcast1Id);
    podcast2.setId(podcast2Id);

    List<Podcast> list = podcastDao.list();

    assertTrue(list.contains(podcast1) && list.contains(podcast2));
  }

}
