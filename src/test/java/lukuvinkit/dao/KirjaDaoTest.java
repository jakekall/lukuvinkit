package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Kirja;
import org.junit.Before;
import org.junit.Test;

public class KirjaDaoTest {

  private Database db;
  private KirjaDao kirjaDao;

  @Before
  public void setup() {
    File dbFile = new File("test.db");
    if (dbFile.exists()) {
      dbFile.delete();
    }

    db = new Database("jdbc:sqlite:test.db");
    kirjaDao = new KirjaDao(db);
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Kirja kirja = new Kirja("Test Kirja 1", "Kirjailija 1", "Ekan kirjan kuvaus");
    int id = kirjaDao.create(kirja);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Kirja kirja1 = new Kirja("Test Kirja 1", "Kirjailija 1", "Ekan kirjan kuvaus");
    Kirja kirja2 = new Kirja("Test Kirja 2", "Kirjailija 2", "Tokan kirjan kuvaus");
    int kirja1Id = kirjaDao.create(kirja1);
    int kirja2Id = kirjaDao.create(kirja2);

    kirja1.setId(kirja1Id);
    kirja2.setId(kirja2Id);

    List<Kirja> list = kirjaDao.list();

    assertTrue(list.contains(kirja1) && list.contains(kirja2));
  }

}
