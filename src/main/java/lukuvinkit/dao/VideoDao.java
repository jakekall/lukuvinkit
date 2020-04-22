package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Video;

public class VideoDao implements Dao<Video, Integer> {

  private final Database db;
  private LukuvinkkiDao lukuvinkkiDao;

  public VideoDao(Database db, LukuvinkkiDao lukuvinkkiDao) {
    this.db = db;
    this.lukuvinkkiDao = lukuvinkkiDao;
  }

  @Override
  public int create(Video video) throws SQLException {
    int id = lukuvinkkiDao.create(video);

    Connection connection = db.getConnection();
    PreparedStatement stmt = connection
            .prepareStatement("INSERT INTO Video (lukuvinkki_id, url)"
                    + " VALUES (?, ?)");
    stmt.setInt(1, id);
    stmt.setString(2, video.getUrl());
    stmt.executeUpdate();

    stmt.close();
    connection.close();

    return id;
  }

  @Override
  public Video read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public Video update(Video video) throws SQLException {
    return null;
  }

  @Override
  public void delete(Integer id) throws SQLException {
    lukuvinkkiDao.delete(id);
  }

  @Override
  public List<Video> list() throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, url, kuvaus, nimi FROM Lukuvinkki "
            + "INNER JOIN Video ON video.lukuvinkki_id = lukuvinkki.id "
            + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
            + "ORDER BY lukuvinkki.id;");
    ResultSet rs = stmt.executeQuery();

    List<Video> videos = new ArrayList<>();
    int prevId = -1;
    Video video = new Video();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String url = rs.getString("url");
        String kuvaus = rs.getString("kuvaus");
        video = new Video(id, otsikko, url, kuvaus, new ArrayList<>());
        videos.add(video);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        video.getTags().add(tag);
      }
    }
    rs.close();
    stmt.close();
    connection.close();

    return videos;
  }

  @Override
  public List<Video> listByTag(String tagFilter) throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, url, kuvaus, nimi FROM Lukuvinkki "
                    + "INNER JOIN Video ON video.lukuvinkki_id = lukuvinkki.id "
                    + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE Lukuvinkki.id IN (SELECT lukuvinkki.id FROM Tagi "
                    + "LEFT JOIN Lukuvinkki ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE tagi.nimi = ?) "
                    + "ORDER BY lukuvinkki.id;");
    stmt.setString(1, tagFilter);
    ResultSet rs = stmt.executeQuery();

    List<Video> videos = new ArrayList<>();
    int prevId = -1;
    Video video = new Video();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String url = rs.getString("url");
        String kuvaus = rs.getString("kuvaus");
        video = new Video(id, otsikko, url, kuvaus, new ArrayList<>());
        videos.add(video);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        video.getTags().add(tag);
      }
    }
    rs.close();
    stmt.close();
    connection.close();

    return videos;
  }
}
