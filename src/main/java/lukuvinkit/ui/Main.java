package lukuvinkit.ui;

import lukuvinkit.domain.LukuvinkkienKasittely;
import lukuvinkit.io.KonsoliIO;

public class Main {
  public static void main(String[] args) {
    Ui ui = new Ui(new KonsoliIO(), new LukuvinkkienKasittely());
    ui.startUi();
  }
}
