package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.TestDatabase;
import lukuvinkit.domain.Video;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VideoDaoTest {

  private TestDatabase db;
  private VideoDao videoDao;

  @Before
  public void setup() throws SQLException {
    db = new TestDatabase();
    LukuvinkkiDao lukuvinkkiDao = new LukuvinkkiDao(db);
    videoDao = new VideoDao(db, lukuvinkkiDao);
  }

  @After
  public void cleanup() throws SQLException {
    db.close();
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Video video = new Video("Test Video 1", "www.example.com",
            "Videon kuvaus", new ArrayList<>());

    int id = videoDao.create(video);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Video video1 = new Video("Test Video 1", "www.example.com",
            "Ekan videon kuvaus", new ArrayList<>());
    Video video2 = new Video("Test Video 2", "www.example.com",
            "Tokan videon kuvaus", new ArrayList<>());

    int videoId1 = videoDao.create(video1);
    int videoId2 = videoDao.create(video2);

    video1.setId(videoId1);
    video2.setId(videoId2);

    List<Video> list = videoDao.list();

    assertTrue(list.contains(video1) && list.contains(video2));
  }

}
