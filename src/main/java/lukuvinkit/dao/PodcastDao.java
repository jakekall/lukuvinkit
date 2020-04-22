package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Podcast;

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
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, url, kuvaus, nimi FROM Lukuvinkki "
            + "INNER JOIN Podcast ON podcast.lukuvinkki_id = lukuvinkki.id "
            + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
            + "ORDER BY lukuvinkki.id;");
    ResultSet rs = stmt.executeQuery();

    List<Podcast> podcasts = new ArrayList<>();
    int prevId = -1;
    Podcast podcast = new Podcast();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String url = rs.getString("url");
        String kuvaus = rs.getString("kuvaus");
        podcast = new Podcast(id, otsikko, url, kuvaus, new ArrayList<>());
        podcasts.add(podcast);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        podcast.getTags().add(tag);
      }
    }
    rs.close();
    stmt.close();
    connection.close();

    return podcasts;
  }

  @Override
  public List<Podcast> listByTag(String tagFilter) throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, url, kuvaus, nimi FROM Lukuvinkki "
                    + "INNER JOIN Podcast ON podcast.lukuvinkki_id = lukuvinkki.id "
                    + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE lukuvinkki.id IN (SELECT lukuvinkki.id FROM Tagi "
                    + "LEFT JOIN Lukuvinkki ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE tagi.nimi = ?) "
                    + "ORDER BY lukuvinkki.id;");
    stmt.setString(1, tagFilter);
    ResultSet rs = stmt.executeQuery();

    List<Podcast> podcasts = new ArrayList<>();
    int prevId = -1;
    Podcast podcast = new Podcast();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String url = rs.getString("url");
        String kuvaus = rs.getString("kuvaus");
        podcast = new Podcast(id, otsikko, url, kuvaus, new ArrayList<>());
        podcasts.add(podcast);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        podcast.getTags().add(tag);
      }
    }
    rs.close();
    stmt.close();
    connection.close();

    return podcasts;
  }
}
