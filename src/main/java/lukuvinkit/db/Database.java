package lukuvinkit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.sqlite.SQLiteConfig;

public class Database {

  private final String databaseUrl;

  public Database(String databaseUrl) {
    this.databaseUrl = databaseUrl;

    init();
  }

  private void init() {
    List<String> commands = sqliteLauseet();

    try (Connection conn = getConnection()) {
      Statement st = conn.createStatement();

      for (String command : commands) {
        st.executeUpdate(command);
      }

    } catch (Throwable t) {
      System.out.println("Error >> " + t.getMessage());
    }
  }

  public Connection getConnection() throws SQLException {
    SQLiteConfig config = new SQLiteConfig();
    config.enforceForeignKeys(true);
    return DriverManager.getConnection(databaseUrl, config.toProperties());
  }

  private List<String> sqliteLauseet() {
    ArrayList<String> commandsList = new ArrayList<>();

    commandsList.add(
        "CREATE TABLE IF NOT EXISTS Lukuvinkki ("
            + "id integer PRIMARY KEY, "
            + "otsikko varchar(255) NOT NULL, "
            + "kuvaus varchar(255), "
            + "luettu integer NOT NULL);"
    );

    commandsList.add(
        "CREATE TABLE IF NOT EXISTS Blogpost ("
            + "lukuvinkki_id integer PRIMARY KEY, "
            + "url varchar(255) NOT NULL, "
            + "FOREIGN KEY(lukuvinkki_id) REFERENCES Lukuvinkki(id) "
            + "ON DELETE CASCADE);"
    );

    commandsList.add(
        "CREATE TABLE IF NOT EXISTS Kirja ("
            + "lukuvinkki_id integer PRIMARY KEY, "
            + "kirjailija varchar(255) NOT NULL, "
            + "FOREIGN KEY(lukuvinkki_id) REFERENCES Lukuvinkki(id) "
            + "ON DELETE CASCADE);"
    );

    commandsList.add(
        "CREATE TABLE IF NOT EXISTS Podcast ("
            + "lukuvinkki_id integer PRIMARY KEY, "
            + "url varchar(255) NOT NULL, "
            + "FOREIGN KEY(lukuvinkki_id) REFERENCES Lukuvinkki(id) "
            + "ON DELETE CASCADE);"
    );

    commandsList.add(
        "CREATE TABLE IF NOT EXISTS Video ("
            + "lukuvinkki_id integer PRIMARY KEY, "
            + "url varchar(255) NOT NULL, "
            + "FOREIGN KEY(lukuvinkki_id) REFERENCES Lukuvinkki(id) "
            + "ON DELETE CASCADE);"
    );

    commandsList.add(
        "CREATE TABLE IF NOT EXISTS Tagi ("
            + "id integer PRIMARY KEY, "
            + "lukuvinkki_id integer, "
            + "nimi varchar(255), "
            + "FOREIGN KEY(lukuvinkki_id) REFERENCES Lukuvinkki(id) "
            + "ON DELETE CASCADE)"
    );

    return commandsList;
  }
}
