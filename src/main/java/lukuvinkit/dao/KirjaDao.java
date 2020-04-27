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
            "SELECT lukuvinkki.id as id, otsikko, kirjailija, kuvaus, nimi, luettu FROM Lukuvinkki "
            + "INNER JOIN Kirja ON kirja.lukuvinkki_id = lukuvinkki.id "
            + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
            + "ORDER BY lukuvinkki.id;");
    ResultSet rs = stmt.executeQuery();
    List<Kirja> books = new ArrayList<>();
    createListFromResultSet(rs, books);
    rs.close();
    stmt.close();
    connection.close();

    return books;
  }

  public List<Kirja> listByTitle(String title) throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
        "SELECT lukuvinkki.id as id, otsikko, kirjailija, kuvaus, nimi, luettu FROM Lukuvinkki "
            + "INNER JOIN Kirja ON kirja.lukuvinkki_id = lukuvinkki.id "
            + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
            + "WHERE otsikko LIKE ? "
            + "ORDER BY lukuvinkki.id;");
    stmt.setString(1, "%" + title + "%");
    ResultSet rs = stmt.executeQuery();
    List<Kirja> books = new ArrayList<>();
    createListFromResultSet(rs, books);
    rs.close();
    stmt.close();
    connection.close();

    return books;
  }

  @Override
  public List<Kirja> listByTag(String tagFilter) throws SQLException {
    Connection connection = db.getConnection();
    PreparedStatement stmt = connection.prepareStatement(
            "SELECT lukuvinkki.id AS id, otsikko, kirjailija, kuvaus, nimi, luettu FROM Lukuvinkki "
                    + "INNER JOIN Kirja ON kirja.lukuvinkki_id = lukuvinkki.id "
                    + "LEFT JOIN Tagi ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE lukuvinkki.id IN ( "
                    + "SELECT lukuvinkki.id FROM Tagi "
                    + "LEFT JOIN Lukuvinkki ON tagi.lukuvinkki_id = lukuvinkki.id "
                    + "WHERE tagi.nimi = ?) "
                    + "ORDER BY lukuvinkki.id;");
    stmt.setString(1, tagFilter);
    ResultSet rs = stmt.executeQuery();
    List<Kirja> books = new ArrayList<>();
    createListFromResultSet(rs, books);
    rs.close();
    stmt.close();
    connection.close();

    return books;
  }

  private void createListFromResultSet(ResultSet rs, List books) throws SQLException {
    int prevId = -1;
    Kirja book = new Kirja();

    while (rs.next()) {
      int id = rs.getInt("id");
      if (id != prevId) {
        String otsikko = rs.getString("otsikko");
        String kirjailija = rs.getString("kirjailija");
        String kuvaus = rs.getString("kuvaus");
        boolean luettu = rs.getInt("luettu") == 1;
        book = new Kirja(id, otsikko, kirjailija, kuvaus, new ArrayList<>(), luettu);
        books.add(book);
        prevId = id;
      }
      String tag = rs.getString("nimi");
      if (tag != null) {
        book.getTags().add(tag);
      }
    }
  }

}
