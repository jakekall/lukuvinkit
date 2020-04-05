package lukuvinkit;

import static org.junit.Assert.assertTrue;

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

  @Then("system will respond with {string}")
  public void systemWillRespondWith(String message) {
    app = new Ui(io, kasittely);
    app.startUi();
    assertTrue(io.getPrints().contains(message));
  }

  @Given("user successfully saves new lukuvinkki {string}")
  public void userSuccessfullySavesNewLukuvinkki(String title) {
    commandIsSelected("1");
    titleIsEntered(title);
    systemWillRespondWith(title + " lisätty!");
  }
}
