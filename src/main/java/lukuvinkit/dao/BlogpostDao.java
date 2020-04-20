package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Blogpost;
import lukuvinkit.util.TagParser;

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
    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Blogpost");
    ResultSet rs = stmt.executeQuery();

    List<Blogpost> blogposts = new ArrayList<>();

    while (rs.next()) {
      int id = rs.getInt("id");
      String otsikko = rs.getString("otsikko");
      String url = rs.getString("url");
      String kuvaus = rs.getString("kuvaus");
      List<String> tags = TagParser.stringToList(rs.getString("tags"));

      blogposts.add(new Blogpost(id, otsikko, url, kuvaus, tags));
    }

    rs.close();
    stmt.close();
    connection.close();

    return blogposts;
  }
}
