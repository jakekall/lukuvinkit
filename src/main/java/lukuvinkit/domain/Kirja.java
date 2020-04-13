package lukuvinkit.domain;

public class Kirja extends Lukuvinkki {

  private String kirjailija;

  public Kirja(String otsikko, String kirjailija) {
    super(LukuvinkkiTyyppi.KIRJA, otsikko);
    this.kirjailija = kirjailija;
  }

  public Kirja(String otsikko, String kirjailija, String kuvaus) {
    super(LukuvinkkiTyyppi.KIRJA, otsikko, kuvaus);
    this.kirjailija = kirjailija;
  }

  public Kirja(int id, String otsikko, String kirjailija) {
    super(id, LukuvinkkiTyyppi.KIRJA, otsikko);
    this.kirjailija = kirjailija;
  }

  public Kirja(int id, String otsikko, String kirjailija, String kuvaus) {
    super(id, LukuvinkkiTyyppi.KIRJA, otsikko, kuvaus);
    this.kirjailija = kirjailija;
  }

  public String getKirjailija() {
    return kirjailija;
  }

  public void setKirjailija(String kirjailija) {
    this.kirjailija = kirjailija;
  }

  @Override
  public String toString() {
    return super.toString() + "\nKirjoittaja: " + kirjailija;
  }
}
