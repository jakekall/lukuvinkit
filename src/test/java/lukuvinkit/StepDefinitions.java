package lukuvinkit;

import static org.junit.Assert.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayDeque;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.StubIO;
import lukuvinkit.ui.Ui;

public class StepDefinitions {

  Ui app;
  LukuvinkkienKasittely kasittely;
  StubIO io;

  @Before
  public void setup() {
    io = new StubIO(new ArrayDeque<>());
    kasittely = new LukuvinkkienKasittely();
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
  public void systemWillRespondWith(String message) {
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(io.getPrints().contains(message));
  }

  @Given("user successfully saves new lukuvinkki with title {string} and url {string}")
  public void userSuccessfullySavesNewLukuvinkki(String title, String url) {
    commandIsSelected("1");
    commandIsSelected("2");
    titleIsEntered(title);
    urlIsEntered(url);
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
  public void systemWillRespondWithWarning(String message) {
    io.enterInput("1");
    io.enterInput("n");
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(io.getPrints().contains(message));
  }
  
  @Then("lukuvinkki is not removed")
  public void isNotRemoved() {
    app = new Ui(io, kasittely);
    app.startUi();
    assertFalse(kasittely.getAllRecommendations().isEmpty());
  }
  
  @Then("lukuvinkki is removed")
  public void isRemoved() {
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(kasittely.getAllRecommendations().isEmpty());
  }
}
