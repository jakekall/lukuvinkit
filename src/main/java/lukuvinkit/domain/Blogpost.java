package lukuvinkit.domain;

public class Blogpost extends Lukuvinkki {
  private String url;

  public Blogpost(String otsikko, String url) {
    super(otsikko);
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
