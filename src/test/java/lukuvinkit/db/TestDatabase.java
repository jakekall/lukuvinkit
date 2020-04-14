package lukuvinkit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase extends Database {

  private Connection conn;
  private String databaseUrl;

  public TestDatabase() {
    super(null);
    databaseUrl = "jdbc:sqlite:file::memory:?cache=shared";
    try {
      conn = DriverManager.getConnection(databaseUrl);
    } catch (SQLException e) {
    }
    init();
  }

  @Override
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(databaseUrl);
  }

  public void close() {
    try {
      conn.close();
    } catch (SQLException e) {
    }
  }

  private void init() {
    List<String> commands = sqliteLauseet();

    try ( Connection conn = getConnection()) {
      Statement st = conn.createStatement();

      for (String command : commands) {
        st.executeUpdate(command);
      }

    } catch (Throwable t) {
      System.out.println("Error >> " + t.getMessage());
    }
  }

  private List<String> sqliteLauseet() {
    ArrayList<String> commandsList = new ArrayList<>();

    commandsList.add(
            "CREATE TABLE Blogpost (id integer PRIMARY KEY, otsikko varchar(255) NOT NULL, url varchar(255) NOT NULL, kuvaus varchar(255), tags varchar(255));");
    commandsList.add(
            "CREATE TABLE Kirja (id integer PRIMARY KEY, otsikko varchar(255) NOT NULL, kirjailija varchar(255) NOT NULL, kuvaus varchar(255), tags varchar(255));");
    commandsList.add(
            "CREATE TABLE Podcast (id integer PRIMARY KEY, otsikko varchar(255) NOT NULL, url varchar(255) NOT NULL, kuvaus varchar(255) NOT NULL, tags varchar(255));");
    commandsList.add(
            "CREATE TABLE Video (id integer PRIMARY KEY, otsikko varchar(255) NOT NULL, url varchar(255) NOT NULL, kuvaus varchar(255), tags varchar(255));");

    return commandsList;
  }
}
