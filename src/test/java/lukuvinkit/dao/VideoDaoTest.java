package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Video;
import org.junit.Before;
import org.junit.Test;

public class VideoDaoTest {

  private Database db;
  private VideoDao videoDao;

  @Before
  public void setup() {
    File dbFile = new File("test.db");
    if (dbFile.exists()) {
      dbFile.delete();
    }

    db = new Database("jdbc:sqlite:test.db");
    videoDao = new VideoDao(db);
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Video video = new Video("Test Video 1", "www.example.com", "Videon kuvaus");
    int id = videoDao.create(video);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Video video1 = new Video("Test Video 1", "www.example.com", "Ekan videon kuvaus");
    Video video2 = new Video("Test Video 2", "www.example.com", "Tokan videon kuvaus");
    int videoId1 = videoDao.create(video1);
    int videoId2 = videoDao.create(video2);

    video1.setId(videoId1);
    video2.setId(videoId2);

    List<Video> list = videoDao.list();

    assertTrue(list.contains(video1) && list.contains(video2));
  }

}
