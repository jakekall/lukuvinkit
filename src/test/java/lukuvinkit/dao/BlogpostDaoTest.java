package lukuvinkit.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.TestDatabase;
import lukuvinkit.domain.Blogpost;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlogpostDaoTest {

  private TestDatabase db;
  private BlogpostDao blogpostDao;

  @Before
  public void setup() throws SQLException {
    db = new TestDatabase();
    LukuvinkkiDao lukuvinkkiDao = new LukuvinkkiDao(db);
    blogpostDao = new BlogpostDao(db, lukuvinkkiDao);
  }

  @After
  public void cleanup() throws SQLException {
    db.close();
  }

  @Test
  public void savesBlogAndReturnsId() throws SQLException {
    Blogpost blogpost = new Blogpost("Test Blog", "www.example.com",
            "Blogin kuvaus", new ArrayList<>());
    
    int id = blogpostDao.create(blogpost);

    assertEquals(1, id);
  }

  @Test
  public void savesTwoBlogsAndReturnsAll() throws SQLException {
    Blogpost blogpost1 = new Blogpost("Test Blog 1", "www.example.com",
            "Ekan blogin kuvaus", new ArrayList<>());
    Blogpost blogpost2 = new Blogpost("Test Blog 2", "www.example.com",
            "Tokan blogin kuvaus", new ArrayList<>());
    
    int blogpost1Id = blogpostDao.create(blogpost1);
    int blogpost2Id = blogpostDao.create(blogpost2);

    blogpost1.setId(blogpost1Id);
    blogpost2.setId(blogpost2Id);

    List<Blogpost> list = blogpostDao.list();

    assertTrue(list.contains(blogpost1) && list.contains(blogpost2));
  }

}
