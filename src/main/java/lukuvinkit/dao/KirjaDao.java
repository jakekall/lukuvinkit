package lukuvinkit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.db.Database;
import lukuvinkit.domain.Kirja;

public class KirjaDao implements Dao<Kirja, Integer> {

  private final Database db;
  private LukuvinkkiDao lukuvinkkiDao;

  public KirjaDao(Database db, LukuvinkkiDao lukuvinkkiDao) {
    this.db = db;
    this.lukuvinkkiDao = lukuvinkkiDao;
  }

  @Override
  public int create(Kirja kirja) throws SQLException {
    int id = lukuvinkkiDao.create(kirja);

    Connection connection = db.getConnection();
    PreparedStatement stmt = connection
            .prepareStatement("INSERT INTO Kirja (lukuvinkki_id, kirjailija)"
                    + " VALUES (?, ?)");
    stmt.setInt(1, id);
    stmt.setString(2, kirja.getKirjailija());
    stmt.executeUpdate();

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
    lukuvinkkiDao.delete(id);
  }

  @Override
  public List<Kirja> list() throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id as id, otsikko, kirjailija, kuvaus, nimi FROM Lukuvinkki "
            + "INNER JOIN Kirja ON kirja.lukuvinkki_id = lukuvinkki.id "
            + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
            + "ORDER BY lukuvinkki.id;");
    ResultSet rs = stmt.executeQuery();

    List<Kirja> books = new ArrayList<>();
    int prevId = -1;
    Kirja book = new Kirja();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String kirjailija = rs.getString("kirjailija");
        String kuvaus = rs.getString("kuvaus");
        book = new Kirja(id, otsikko, kirjailija, kuvaus, new ArrayList<>());
        books.add(book);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        book.getTags().add(tag);
      }
    }
    rs.close();
    stmt.close();
    connection.close();

    return books;
  }

}
