package lukuvinkit.domain;

public class Lukuvinkki {
  private String otsikko;
  private String tyyppi;
  private String url;
  private String nimi;
  private String kirjoittaja;


  public Lukuvinkki(String otsikko) {
    this.otsikko = otsikko;
  }

  public String getOtsikko() {
    return otsikko;
  }

  public void setOtsikko(String otsikko) {
    this.otsikko = otsikko;
  }

  public String toString() {
    return "Otsikko: " + this.otsikko;
  }
}

