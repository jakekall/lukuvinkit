package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Video;
import lukuvinkit.util.TagParser;

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
    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Video");
    ResultSet rs = stmt.executeQuery();

    List<Video> videos = new ArrayList<>();

    while (rs.next()) {
      int id = rs.getInt("id");
      String otsikko = rs.getString("otsikko");
      String url = rs.getString("url");
      String kuvaus = rs.getString("kuvaus");
      List<String> tags = TagParser.stringToList(rs.getString("tags"));

      videos.add(new Video(id, otsikko, url, kuvaus, tags));
    }

    rs.close();
    stmt.close();
    connection.close();

    return videos;
  }
}
