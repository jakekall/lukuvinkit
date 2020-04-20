package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.TestDatabase;
import lukuvinkit.domain.Kirja;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KirjaDaoTest {

  private TestDatabase db;
  private KirjaDao kirjaDao;

  @Before
  public void setup() throws SQLException {
    db = new TestDatabase();
    LukuvinkkiDao lukuvinkkiDao = new LukuvinkkiDao(db);
    kirjaDao = new KirjaDao(db, lukuvinkkiDao);
  }

  @After
  public void cleanup() throws SQLException {
    db.close();
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Kirja kirja = new Kirja("Test Kirja 1", "Kirjailija 1",
            "Ekan kirjan kuvaus", new ArrayList<>());

    int id = kirjaDao.create(kirja);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Kirja kirja1 = new Kirja("Test Kirja 1", "Kirjailija 1",
            "Ekan kirjan kuvaus", new ArrayList<>());
    Kirja kirja2 = new Kirja("Test Kirja 2", "Kirjailija 2",
            "Tokan kirjan kuvaus", new ArrayList<>());

    int kirja1Id = kirjaDao.create(kirja1);
    int kirja2Id = kirjaDao.create(kirja2);

    kirja1.setId(kirja1Id);
    kirja2.setId(kirja2Id);

    List<Kirja> list = kirjaDao.list();

    assertTrue(list.contains(kirja1) && list.contains(kirja2));
  }

}
