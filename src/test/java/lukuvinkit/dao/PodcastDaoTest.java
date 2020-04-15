package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.TestDatabase;
import lukuvinkit.domain.Podcast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PodcastDaoTest {

  private TestDatabase db;
  private PodcastDao podcastDao;

  @Before
  public void setup() throws SQLException {
    db = new TestDatabase();
    podcastDao = new PodcastDao(db);
  }

  @After
  public void cleanup() throws SQLException {
    db.close();
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Podcast kirja = new Podcast("Test Podcast 1", "www.example.com",
            "Podcastin kuvaus", new ArrayList<>());

    int id = podcastDao.create(kirja);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Podcast podcast1 = new Podcast("Test Podcast 1", "www.example.com",
            "Ekan podcastin kuvaus", new ArrayList<>());
    Podcast podcast2 = new Podcast("Test Podcast 2", "www.example.com",
            "Tokan podcastin kuvaus", new ArrayList<>());

    int podcast1Id = podcastDao.create(podcast1);
    int podcast2Id = podcastDao.create(podcast2);

    podcast1.setId(podcast1Id);
    podcast2.setId(podcast2Id);

    List<Podcast> list = podcastDao.list();

    assertTrue(list.contains(podcast1) && list.contains(podcast2));
  }

}
