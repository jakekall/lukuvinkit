package lukuvinkit.domain;

import java.util.List;

public class Kirja extends Lukuvinkki {

  private String kirjailija;

  public Kirja(String otsikko, String kirjailija, List<String> tags) {
    super(LukuvinkkiTyyppi.KIRJA, otsikko, tags);
    this.kirjailija = kirjailija;
  }

  public Kirja(String otsikko, String kirjailija, String kuvaus, List<String> tags) {
    super(LukuvinkkiTyyppi.KIRJA, otsikko, kuvaus, tags);
    this.kirjailija = kirjailija;
  }

  public Kirja(int id, String otsikko, String kirjailija, List<String> tags) {
    super(id, LukuvinkkiTyyppi.KIRJA, otsikko, tags);
    this.kirjailija = kirjailija;
  }

  public Kirja(int id, String otsikko, String kirjailija, String kuvaus, List<String> tags) {
    super(id, LukuvinkkiTyyppi.KIRJA, otsikko, kuvaus, tags);
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
