package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Video;

public class VideoDao implements Dao<Video, Integer> {

  private Database db;

  public VideoDao(Database db) {
    this.db = db;
  }

  @Override
  public int create(Video video) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
        .prepareStatement("INSERT INTO Video (otsikko, url, kuvaus) VALUES (?, ?, ?)");

    stmt.setString(1, video.getOtsikko());
    stmt.setString(2, video.getUrl());
    stmt.setString(3, video.getKuvaus());

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

      videos.add(new Video(id, otsikko, url, kuvaus));
    }

    rs.close();
    stmt.close();
    connection.close();

    return videos;
  }
}
