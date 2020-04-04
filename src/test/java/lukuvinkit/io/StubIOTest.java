package lukuvinkit.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import org.junit.Before;
import org.junit.Test;

public class StubIOTest {
  ArrayDeque<String> commands;
  StubIO io;

  @Before
  public void setup() {
    commands = new ArrayDeque<>();
    io = new StubIO(commands);
  }

  @Test
  public void stubIOStoresAddedPromptForPrinting() {
    io.print("Hello world!");
    assertTrue(io.getPrints().contains("Hello world!"));
  }

  @Test
  public void stubStoresAndReturnsAddedCommand() {
    ArrayDeque<String> commands = new ArrayDeque<>();
    commands.add("1");

    StubIO io = new StubIO(commands);

    assertEquals("1", io.nextCommand());
  }

  @Test
  public void stubStoresManyAndReturnsFirstAddedCommand() {
    ArrayDeque<String> commands = new ArrayDeque<>();
    commands.add("2");
    commands.add("1");
    commands.add("test");
    commands.add("3");

    StubIO io = new StubIO(commands);

    assertEquals("2", io.nextCommand());
  }
}
