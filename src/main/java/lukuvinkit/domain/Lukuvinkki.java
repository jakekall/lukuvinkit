package lukuvinkit.domain;

import java.util.List;

public class Lukuvinkki {

  private int id;
  private LukuvinkkiTyyppi tyyppi;
  private String otsikko;
  private String kuvaus;
  private List<String> tags;

  public Lukuvinkki(LukuvinkkiTyyppi tyyppi, String otsikko, List<String> tags) {
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
    this.tags = tags;
  }

  public Lukuvinkki(LukuvinkkiTyyppi tyyppi, String otsikko, String kuvaus, List<String> tags) {
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
    this.kuvaus = kuvaus;
    this.tags = tags;
  }

  public Lukuvinkki(int id, LukuvinkkiTyyppi tyyppi, String otsikko, List<String> tags) {
    this.id = id;
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
    this.tags = tags;
  }

  public Lukuvinkki(int id, LukuvinkkiTyyppi tyyppi, String otsikko, String kuvaus, List<String> tags) {
    this.id = id;
    this.tyyppi = tyyppi;
    this.otsikko = otsikko;
    this.kuvaus = kuvaus;
    this.tags = tags;
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

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
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

