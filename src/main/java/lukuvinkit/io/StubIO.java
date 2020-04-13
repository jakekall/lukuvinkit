package lukuvinkit.io;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

  private ArrayDeque<String> inputLines;
  private List<String> prints;

  public StubIO(ArrayDeque<String> inputLines) {
    this.inputLines = inputLines;
    this.prints = new ArrayList<>();
  }

  @Override
  public void print(String prompt) {
    prints.add(prompt);
  }

  public List<String> getPrints() {
    return prints;
  }

  public void enterInput(String input) {
    inputLines.add(input);
  }

  @Override
  public String nextCommand() {
    if (inputLines.isEmpty()) {
      return "4";
    }
    return inputLines.pollFirst();
  }
}
