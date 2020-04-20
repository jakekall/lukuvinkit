package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Podcast;
import lukuvinkit.util.TagParser;

public class PodcastDao implements Dao<Podcast, Integer> {

  private final Database db;
  private LukuvinkkiDao lukuvinkkiDao;

  public PodcastDao(Database db, LukuvinkkiDao lukuvinkkiDao) {
    this.db = db;
    this.lukuvinkkiDao = lukuvinkkiDao;
  }

  @Override
  public int create(Podcast podcast) throws SQLException {
    int id = lukuvinkkiDao.create(podcast);
    
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection
            .prepareStatement("INSERT INTO Podcast (lukuvinkki_id, url)"
                    + " VALUES (?, ?)");
    stmt.setInt(1, id);
    stmt.setString(2, podcast.getUrl());
    stmt.executeUpdate();
    
    stmt.close();
    connection.close();
    
    return id;
  }

  @Override
  public Podcast read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public Podcast update(Podcast podcast) throws SQLException {
    return null;
  }

  @Override
  public void delete(Integer id) throws SQLException {
    lukuvinkkiDao.delete(id);
  }

  @Override
  public List<Podcast> list() throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Podcast");
    ResultSet rs = stmt.executeQuery();

    List<Podcast> podcasts = new ArrayList<>();

    while (rs.next()) {
      int id = rs.getInt("id");
      String otsikko = rs.getString("otsikko");
      String url = rs.getString("url");
      String kuvaus = rs.getString("kuvaus");
      List<String> tags = TagParser.stringToList(rs.getString("tags"));

      podcasts.add(new Podcast(id, otsikko, url, kuvaus, tags));
    }

    rs.close();
    stmt.close();
    connection.close();

    return podcasts;
  }
}
