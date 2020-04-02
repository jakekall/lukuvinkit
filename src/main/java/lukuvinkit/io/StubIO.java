package lukuvinkit.io;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

  private ArrayDeque<String> commands;
  private List<String> prints;

  public StubIO(ArrayDeque<String> lines) {
    this.commands = lines;
    this.prints = new ArrayList<>();
  }

  @Override
  public void print(String prompt) {
    prints.add(prompt);
  }

  public List<String> getPrints() {
    return prints;
  }

  @Override
  public String nextCommand() {
    return commands.pollFirst();
  }
}
