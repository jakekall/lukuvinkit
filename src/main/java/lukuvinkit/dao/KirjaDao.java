package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Kirja;
import lukuvinkit.util.TagParser;

public class KirjaDao implements Dao<Kirja, Integer> {

  private Database db;

  public KirjaDao(Database db) {
    this.db = db;
  }

  @Override
  public int create(Kirja kirja) throws SQLException {
    System.out.println("Saving...");
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
            .prepareStatement("INSERT INTO Kirja (otsikko, kirjailija, kuvaus, tags) VALUES (?, ?, ?, ?)");

    stmt.setString(1, kirja.getOtsikko());
    stmt.setString(2, kirja.getKirjailija());
    stmt.setString(3, kirja.getKuvaus());
    stmt.setString(4, TagParser.listToString(kirja.getTags()));

    stmt.executeUpdate();

    int id = -1;
    ResultSet generatedKeys = stmt.getGeneratedKeys();

    System.out.println(generatedKeys);

    if (generatedKeys.next()) {
      id = generatedKeys.getInt(1);
    }

    System.out.println("id: " + id);

    generatedKeys.close();
    stmt.close();
    connection.close();

    return id;
  }

  @Override
  public Kirja read(Integer id) throws SQLException {
    return null;
  }

  @Override
  public Kirja update(Kirja kirja) throws SQLException {
    return null;
  }

  @Override
  public void delete(Integer id) throws SQLException {
    Connection connection = db.getConnection();

    PreparedStatement stmt = connection
            .prepareStatement("DELETE FROM Kirja WHERE id = ?");

    stmt.setInt(1, id);
    stmt.executeUpdate();

    stmt.close();
    connection.close();
  }

  @Override
  public List<Kirja> list() throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kirja");
    ResultSet rs = stmt.executeQuery();

    List<Kirja> books = new ArrayList<>();

    while (rs.next()) {
      int id = rs.getInt("id");
      String otsikko = rs.getString("otsikko");
      String kirjailija = rs.getString("kirjailija");
      String kuvaus = rs.getString("kuvaus");
      List<String> tags = TagParser.stringToList(rs.getString("tags"));
      
      books.add(new Kirja(id, otsikko, kirjailija, kuvaus, tags));
    }

    rs.close();
    stmt.close();
    connection.close();

    return books;
  }

}
