package lukuvinkit.domain;

import java.util.List;

public class Podcast extends Lukuvinkki {

  private String url;

  public Podcast() {
    super(null, null, null);
  }
  
  public Podcast(String otsikko, String url, String kuvaus, List<String> tags) {
    super(LukuvinkkiTyyppi.PODCAST, otsikko, kuvaus, tags);
    this.url = url;
  }

  public Podcast(int id, String otsikko, String url, String kuvaus, List<String> tags, boolean isRead) {
    super(id, LukuvinkkiTyyppi.PODCAST, otsikko, kuvaus, tags, isRead);
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
