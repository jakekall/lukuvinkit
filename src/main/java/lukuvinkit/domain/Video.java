package lukuvinkit.domain;

public class Video extends Lukuvinkki {
  private String url;

  public Video(String otsikko, String url) {
    super(otsikko);
    this.url = url;
  }

  public String toString() {
    return super.toString(); // + "\nurl: " + url;
  }
}
