package lukuvinkit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.sql.SQLException;
import java.util.ArrayDeque;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;
import lukuvinkit.db.TestDatabase;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.StubIO;
import lukuvinkit.ui.Ui;

public class StepDefinitions {

  Ui app;
  LukuvinkkienKasittely kasittely;
  StubIO io;
  TestDatabase testDatabase;

  @Before
  public void setup() throws SQLException {
    io = new StubIO(new ArrayDeque<>());
    testDatabase = new TestDatabase();

    BlogpostDao blogpostDao = new BlogpostDao(testDatabase);
    KirjaDao kirjaDao = new KirjaDao(testDatabase);
    PodcastDao podcastDao = new PodcastDao(testDatabase);
    VideoDao videoDao = new VideoDao(testDatabase);
    kasittely = new LukuvinkkienKasittely(blogpostDao, kirjaDao, podcastDao, videoDao);
  }

  @After
  public void cleanup() throws SQLException {
    testDatabase.close();
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

  @When("tags {string} are entered")
  public void tagsAreEntered(String tags) {
    io.enterInput(tags);
  }

  @When("description {string} is entered")
  public void descriptionIsEntered(String description) {
    io.enterInput(description);
  }

  @Then("system will respond with {string}")
  public void systemWillRespondWith(String message) throws SQLException {
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(io.getPrints().contains(message));
  }

  @Given("user successfully saves new lukuvinkki with title {string}"
          + " url {string} description {string} and tags {string}")
  public void userSuccessfullySavesNewLukuvinkki(String title, String url,
          String description, String tags) {
    commandIsSelected("1");
    commandIsSelected("2");
    titleIsEntered(title);
    urlIsEntered(url);
    descriptionIsEntered(description);
    tagsAreEntered(tags);
  }

  @When("index {string} is entered")
  public void indexIsEntered(String index) {
    io.enterInput(index);
  }

  @When("confirmation {string} is entered")
  public void confitmationIsEntered(String confirmation) {
    io.enterInput(confirmation);
  }

  @Then("system will respond with warning {string}")
  public void systemWillRespondWithWarning(String message) throws SQLException {
    io.enterInput("1");
    io.enterInput("n");
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(io.getPrints().contains(message));
  }

  @Then("lukuvinkki is not removed")
  public void isNotRemoved() throws SQLException {
    app = new Ui(io, kasittely);
    app.startUi();
    assertFalse(kasittely.getAllRecommendations().isEmpty());
  }

  @Then("lukuvinkki is removed")
  public void isRemoved() throws SQLException {
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(kasittely.getAllRecommendations().isEmpty());
  }

  @Given("user successfully saves a new blogpost with title {string} and url {string}")
  public void userSuccesfullySavesABlogpost(String title, String url) {
    commandIsSelected("1");
    commandIsSelected("3");
    titleIsEntered(title);
    urlIsEntered(url);
    descriptionIsEntered("");
    tagsAreEntered("");
  }

}
