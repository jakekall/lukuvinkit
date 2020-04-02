package lukuvinkit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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
  ArrayDeque<String> inputLines;

  @Before
  public void setup() {
    inputLines = new ArrayDeque<>();
  }

  @Given("^command lisaa lukuvinkki is selected$")
  public void command_lisaa_lukuvinkki_is_selected() {
    inputLines.add("1");
    assertThat("1", is(inputLines.peek()));
  }

  @When("title {string} is entered")
  public void title_is_entered(String title) {
    inputLines.add(title);
    inputLines.add("3");

    io = new StubIO(inputLines);
    kasittely = new LukuvinkkienKasittely();
    app = new Ui(io, kasittely);
    app.startUi();
  }

  @Then("system will respond with {string}")
  public void systemWillRespondWith(String message) {
    assertTrue(io.getPrints().contains(message));
  }
}
