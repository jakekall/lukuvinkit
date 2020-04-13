package lukuvinkit;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayDeque;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.StubIO;
import lukuvinkit.ui.Ui;
import org.junit.After;

public class StepDefinitions {

  Ui app;
  LukuvinkkienKasittely kasittely;
  StubIO io;

  @Before
  public void setup() {
    io = new StubIO(new ArrayDeque<>());
    Database testDatabase = new Database("jdbc:sqlite:test.db");

    BlogpostDao blogpostDao = new BlogpostDao(testDatabase);
    KirjaDao kirjaDao = new KirjaDao(testDatabase);
    PodcastDao podcastDao = new PodcastDao(testDatabase);
    VideoDao videoDao = new VideoDao(testDatabase);
    kasittely = new LukuvinkkienKasittely(blogpostDao, kirjaDao, podcastDao, videoDao);
  }

  @Given("command {string} is selected")
  public void commandIsSelected(String command) {
    io.enterInput(command);
  }

  @When("title {string} is entered")
  public void titleIsEntered(String title) {
    io.enterInput(title);
  }

  @When("url {string} is entered")
  public void urlIsEntered(String url) {
    io.enterInput(url);
  }

  @Then("system will respond with {string}")
  public void systemWillRespondWith(String message) throws SQLException {
    app = new Ui(io, kasittely);
    app.startUi();
    System.out.println(io.getPrints());
    assertTrue(io.getPrints().contains(message));
  }

  @Given("user successfully saves new lukuvinkki with title {string} and url {string}")
  public void userSuccessfullySavesNewLukuvinkki(String title, String url) {
    commandIsSelected("1");
    commandIsSelected("2");
    titleIsEntered(title);
    urlIsEntered(url);
  }

  @After
  public void removeDatabase() {
    File dbFile = new File("test.db");

    if (dbFile.exists()) {
      dbFile.delete();
    }
  }

}
