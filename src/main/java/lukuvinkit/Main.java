package lukuvinkit;

import java.util.Scanner;
import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.KonsoliIO;
import lukuvinkit.ui.Ui;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Ui ui = new Ui(new KonsoliIO(scanner), new LukuvinkkienKasittely());
    ui.startUi();
  }
}
