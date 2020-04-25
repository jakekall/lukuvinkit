package lukuvinkit.domain;

import java.util.List;

public class Blogpost extends Lukuvinkki {

  private String url;

  public Blogpost() {
    super(null, null, null);
  }
  
  public Blogpost(String otsikko, String url, List<String> tags) {
    super(LukuvinkkiTyyppi.BLOGPOST, otsikko, tags);
    this.url = url;
  }

  public Blogpost(String otsikko, String url, String kuvaus, List<String> tags) {
    super(LukuvinkkiTyyppi.BLOGPOST, otsikko, kuvaus, tags);
    this.url = url;
  }

  public Blogpost(int id, String otsikko, String url, List<String> tags, boolean isRead) {
    super(id, LukuvinkkiTyyppi.BLOGPOST, otsikko, tags, isRead);
    this.url = url;
  }

  public Blogpost(int id, String otsikko, String url, String kuvaus, List<String> tags, boolean isRead) {
    super(id, LukuvinkkiTyyppi.BLOGPOST, otsikko, kuvaus, tags, isRead);
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
