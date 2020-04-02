package lukuvinkit.io;

import java.util.Scanner;

public class KonsoliIO implements IO {
  private Scanner scanner;

  public KonsoliIO(Scanner scanner) {
    this.scanner = scanner;
  }

  @Override
  public void print(String s) {
    System.out.println(s);
  }

  @Override
  public String nextCommand() {
    return scanner.nextLine();
  }
}
