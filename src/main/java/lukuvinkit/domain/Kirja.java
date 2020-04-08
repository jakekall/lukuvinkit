package lukuvinkit.domain;

public class Kirja extends Lukuvinkki {
  private String kirjailija;

  public Kirja(String otsikko, String kirjailija) {
    super(otsikko);
    this.kirjailija = kirjailija;
  }

  @Override
  public String toString() {
    return super.toString() + "\nKirjoittaja: " + kirjailija;
  }
}
