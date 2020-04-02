package lukuvinkit;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
    plugin = "pretty",
    features = "src/test/resources/lukuvinkit",
    snippets = SnippetType.CAMELCASE)
public class RunCucumberTest {}
