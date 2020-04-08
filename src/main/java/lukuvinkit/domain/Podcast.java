package lukuvinkit.domain;

public class Podcast extends Lukuvinkki {
  private String url;

  public Podcast(String otsikko, String url) {
    super(otsikko);
    this.url = url;
  }

  @Override
  public String toString() {
    return super.toString() + "\nurl: " + url;
  }
}
