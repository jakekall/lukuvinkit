package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Blogpost;

public class BlogpostDao implements Dao<Blogpost, Integer> {

  private final Database db;
  private LukuvinkkiDao lukuvinkkiDao;

  public BlogpostDao(Database db, LukuvinkkiDao lukuvinkkiDao) {
    this.db = db;
    this.lukuvinkkiDao = lukuvinkkiDao;
  }

  @Override
  public int create(Blogpost blogpost) throws SQLException {
    int id = lukuvinkkiDao.create(blogpost);
    
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection
            .prepareStatement("INSERT INTO Blogpost (lukuvinkki_id, url)"
                    + " VALUES (?, ?)");
    stmt.setInt(1, id);
    stmt.setString(2, blogpost.getUrl());
    stmt.executeUpdate();
    
    stmt.close();
    connection.close();
    
    return id;
  }

  @Override
  public Blogpost read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public Blogpost update(Blogpost blogpost) throws SQLException {
    return null;
  }

  @Override
  public void delete(Integer id) throws SQLException {
    lukuvinkkiDao.delete(id);
  }

  @Override
  public List<Blogpost> list() throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, url, kuvaus, nimi FROM Lukuvinkki "
            + "INNER JOIN Blogpost ON blogpost.lukuvinkki_id = lukuvinkki.id "
            + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
            + "ORDER BY lukuvinkki.id;");
    ResultSet rs = stmt.executeQuery();
    List<Blogpost> blogs = new ArrayList<>();
    createListFromResultSet(rs, blogs);
    rs.close();
    stmt.close();
    connection.close();

    return blogs;
  }

  @Override
  public List<Blogpost> listByTag(String tagFilter) throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, url, kuvaus, nimi FROM Lukuvinkki "
                    + "INNER JOIN Blogpost ON blogpost.lukuvinkki_id = lukuvinkki.id "
                    + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE lukuvinkki.id IN (SELECT lukuvinkki.id FROM Tagi "
                    + "LEFT JOIN Lukuvinkki ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE tagi.nimi = ?) "
                    + "ORDER BY lukuvinkki.id;");
    stmt.setString(1, tagFilter);
    ResultSet rs = stmt.executeQuery();
    List<Blogpost> blogs = new ArrayList<>();
    createListFromResultSet(rs, blogs);
    rs.close();
    stmt.close();
    connection.close();

    return blogs;
  }

  private void createListFromResultSet(ResultSet rs, List blogs) throws SQLException {
    int prevId = -1;
    Blogpost blogpost = new Blogpost();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String url = rs.getString("url");
        String kuvaus = rs.getString("kuvaus");
        blogpost = new Blogpost(id, otsikko, url, kuvaus, new ArrayList<>());
        blogs.add(blogpost);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        blogpost.getTags().add(tag);
      }
    }
  }
}
