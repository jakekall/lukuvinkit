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

  private Database db;

  public VideoDao(Database db) {
    this.db = db;
  }

  @Override
  public int create(Video video) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
        .prepareStatement("INSERT INTO Video (otsikko, url, kuvaus, tags) VALUES (?, ?, ?, ?)");

    stmt.setString(1, video.getOtsikko());
    stmt.setString(2, video.getUrl());
    stmt.setString(3, video.getKuvaus());
    stmt.setString(4, TagParser.listToString(video.getTags()));

    stmt.executeUpdate();

    int id = -1;
    ResultSet generatedKeys = stmt.getGeneratedKeys();

    if (generatedKeys.next()) {
      id = generatedKeys.getInt(1);
    }

    generatedKeys.close();
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
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
            .prepareStatement("DELETE FROM Video WHERE id = ?");

    stmt.setInt(1, id);
    stmt.executeUpdate();

    stmt.close();
    connection.close();
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
