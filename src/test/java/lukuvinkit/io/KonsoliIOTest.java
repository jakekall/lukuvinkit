package lukuvinkit.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

public class KonsoliIOTest {

  Scanner scanner;
  KonsoliIO io;

  @Before
  public void setup() {
    scanner = new Scanner("1");
    io = new KonsoliIO(scanner);
  }

  @Test
  public void konsoliPrintsRightText() throws IOException {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(outContent);

    System.setOut(ps);

    io.print("Hello world!");

    ps.close();
    outContent.close();

    String expected = "Hello world!\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  public void konsoliReturnsRightCommand() {
    assertEquals("1", io.nextCommand());
  }
}
