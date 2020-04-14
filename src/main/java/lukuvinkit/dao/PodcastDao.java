package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Podcast;
import lukuvinkit.util.TagParser;

public class PodcastDao implements Dao<Podcast, Integer> {

  private Database db;

  public PodcastDao(Database db) {
    this.db = db;
  }

  @Override
  public int create(Podcast podcast) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
            .prepareStatement("INSERT INTO Kirja (otsikko, url, kuvaus, tags) VALUES (?, ?, ?, ?)");

    stmt.setString(1, podcast.getOtsikko());
    stmt.setString(2, podcast.getUrl());
    stmt.setString(3, podcast.getKuvaus());
    stmt.setString(4, TagParser.listToString(podcast.getTags()));

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
  public Podcast read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public Podcast update(Podcast podcast) throws SQLException {
    return null;
  }

  @Override
  public void delete(Integer id) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
            .prepareStatement("DELETE FROM Podcast WHERE id = ?");

    stmt.setInt(1, id);
    stmt.executeUpdate();

    stmt.close();
    connection.close();
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
