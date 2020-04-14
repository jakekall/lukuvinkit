package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Podcast;

public class PodcastDao implements Dao<Podcast, Integer> {

  private Database db;

  public PodcastDao(Database db) {
    this.db = db;
  }

  @Override
  public int create(Podcast podcast) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
        .prepareStatement("INSERT INTO Kirja (otsikko, url, kuvaus) VALUES (?, ?, ?)");

    stmt.setString(1, podcast.getOtsikko());
    stmt.setString(2, podcast.getUrl());
    stmt.setString(3, podcast.getKuvaus());

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

      podcasts.add(new Podcast(id, otsikko, url, kuvaus));
    }

    rs.close();
    stmt.close();
    connection.close();

    return podcasts;
  }
}
