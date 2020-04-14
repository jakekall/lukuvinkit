package lukuvinkit.domain;

public class Podcast extends Lukuvinkki {

  private String url;

  public Podcast(String otsikko, String url, String kuvaus) {
    super(LukuvinkkiTyyppi.PODCAST, otsikko, kuvaus);
    this.url = url;
  }

  public Podcast(int id, String otsikko, String url, String kuvaus) {
    super(id, LukuvinkkiTyyppi.PODCAST, otsikko, kuvaus);
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return super.toString() + "\nurl: " + url;
  }
}
