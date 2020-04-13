package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.Database;
import lukuvinkit.domain.Kirja;

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
        .prepareStatement("INSERT INTO Kirja (otsikko, kirjailija, kuvaus) VALUES (?, ?, ?)");

    stmt.setString(1, kirja.getOtsikko());
    stmt.setString(2, kirja.getKirjailija());
    stmt.setString(3, kirja.getKuvaus());

    stmt.executeUpdate();

    int id = -1;
    ResultSet generatedKeys = stmt.getGeneratedKeys();

    System.out.println(generatedKeys);

    if(generatedKeys.next()) {
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

      books.add(new Kirja(id, otsikko, kirjailija, kuvaus));
    }

    rs.close();
    stmt.close();
    connection.close();

    return books;
  }

}
