package lukuvinkit.domain;

public class Lukuvinkki {

  private int id;
  private LukuvinkkiTyyppi tyyppi;
  private String otsikko;
  private String kuvaus;

  public Lukuvinkki(LukuvinkkiTyyppi tyyppi, String otsikko) {
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
  }

  public Lukuvinkki(LukuvinkkiTyyppi tyyppi, String otsikko, String kuvaus) {
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
    this.kuvaus = kuvaus;
  }

  public Lukuvinkki(int id, LukuvinkkiTyyppi tyyppi, String otsikko) {
    this.id = id;
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
  }

  public Lukuvinkki(int id, LukuvinkkiTyyppi tyyppi, String otsikko, String kuvaus) {
    this.id = id;
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
    this.kuvaus = kuvaus;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LukuvinkkiTyyppi getTyyppi() {
    return tyyppi;
  }

  public void setTyyppi(LukuvinkkiTyyppi tyyppi) {
    this.tyyppi = tyyppi;
  }

  public String getOtsikko() {
    return otsikko;
  }

  public void setOtsikko(String otsikko) {
    this.otsikko = otsikko;
  }

  public String getKuvaus() {
    return kuvaus;
  }

  public void setKuvaus(String kuvaus) {
    this.kuvaus = kuvaus;
  }

  @Override
  public String toString() {
    return "Lukuvinkki{" +
        "id=" + id +
        ", tyyppi=" + tyyppi +
        ", otsikko='" + otsikko + '\'' +
        ", kuvaus='" + kuvaus + '\'' +
        '}';
  }
}

