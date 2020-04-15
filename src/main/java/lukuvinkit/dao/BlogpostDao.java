package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Blogpost;

public class BlogpostDao implements Dao<Blogpost, Integer> {

  private final Database db;

  public BlogpostDao(Database db) {
    this.db = db;
  }

  @Override
  public int create(Blogpost blogpost) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
        .prepareStatement("INSERT INTO Blogpost (otsikko, url, kuvaus) VALUES (?, ?, ?)");

    stmt.setString(1, blogpost.getOtsikko());
    stmt.setString(2, blogpost.getUrl());
    stmt.setString(3, blogpost.getKuvaus());

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
  public Blogpost read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public Blogpost update(Blogpost blogpost) throws SQLException {
    return null;
  }

  @Override
  public void delete(Integer id) throws SQLException {

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

      blogposts.add(new Blogpost(id, otsikko, url, kuvaus));
    }

    rs.close();
    stmt.close();
    connection.close();

    return blogposts;
  }
}
