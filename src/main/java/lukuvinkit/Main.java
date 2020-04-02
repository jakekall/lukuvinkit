package lukuvinkit;

import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.KonsoliIO;
import lukuvinkit.ui.Ui;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Ui ui = new Ui(new KonsoliIO(scanner), new LukuvinkkienKasittely());
    ui.startUi();
  }
}
