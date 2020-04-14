package lukuvinkit.domain;

import java.util.List;

public class Video extends Lukuvinkki {

  private String url;

  public Video(String otsikko, String url, List<String> tags) {
    super(LukuvinkkiTyyppi.VIDEO, otsikko, tags);
    this.url = url;
  }

  public Video(String otsikko, String url, String kuvaus, List<String> tags) {
    super(LukuvinkkiTyyppi.VIDEO, otsikko, kuvaus, tags);
    this.url = url;
  }

  public Video(int id, String otsikko, String url, List<String> tags) {
    super(id, LukuvinkkiTyyppi.VIDEO, otsikko, tags);
    this.url = url;
  }

  public Video(int id, String otsikko, String url, String kuvaus, List<String> tags) {
    super(id, LukuvinkkiTyyppi.VIDEO, otsikko, kuvaus, tags);
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
