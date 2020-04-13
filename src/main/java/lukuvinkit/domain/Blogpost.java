package lukuvinkit.domain;

public class Blogpost extends Lukuvinkki {
  private String url;

  public Blogpost(String otsikko, String url) {
    super(LukuvinkkiTyyppi.BLOGPOST, otsikko);
    this.url = url;
  }

  public Blogpost(String otsikko, String url, String kuvaus) {
    super(LukuvinkkiTyyppi.BLOGPOST, otsikko, kuvaus);
    this.url = url;
  }

  public Blogpost(int id, String otsikko, String url) {
    super(id, LukuvinkkiTyyppi.BLOGPOST, otsikko);
    this.url = url;
  }

  public Blogpost(int id, String otsikko, String url, String kuvaus) {
    super(id, LukuvinkkiTyyppi.BLOGPOST, otsikko, kuvaus);
    this.url = url;
  }

  public String getUrl() {
    return this.url;
  }

  @Override
  public String toString() {
    return super.toString() + "\nurl: url";
  }
}
