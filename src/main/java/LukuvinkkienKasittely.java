import java.util.ArrayList;

public class LukuvinkkienKasittely implements LukuvinkkienKasittelyIF {
  private ArrayList<Lukuvinkki> list;

  public LukuvinkkienKasittely() {
    this.list = new ArrayList<>();
  }

  @Override
  public void saveRecommendation(Lukuvinkki l) {
    list.add(l);
  }

  @Override
  public void deleteRecommendation(Lukuvinkki l) {}

  @Override
  public void editRecommendation(Lukuvinkki l) {}

  @Override
  public ArrayList<Lukuvinkki> getAllRecommendations() {
    return list;
  }
}
