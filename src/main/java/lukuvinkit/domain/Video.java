package lukuvinkit.domain;

public class Video extends Lukuvinkki {

  private String url;

  public Video(String otsikko, String url) {
    super(LukuvinkkiTyyppi.VIDEO, otsikko);
    this.url = url;
  }

  public Video(String otsikko, String url, String kuvaus) {
    super(LukuvinkkiTyyppi.VIDEO, otsikko, kuvaus);
    this.url = url;
  }

  public Video(int id, String otsikko, String url) {
    super(id, LukuvinkkiTyyppi.VIDEO, otsikko);
    this.url = url;
  }

  public Video(int id, String otsikko, String url, String kuvaus) {
    super(id, LukuvinkkiTyyppi.VIDEO, otsikko, kuvaus);
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String toString() {
    return super.toString(); // + "\nurl: " + url;
  }
}
