package lukuvinkit.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LukuvinkkienKasittelyTest {

  @Test
  public void lukuvinkkienKasittelySavesLukuvinkki() {
    LukuvinkkienKasittely lukuvinkkienKasittely = new LukuvinkkienKasittely();

    Lukuvinkki lv = new Lukuvinkki("Clean Code");
    lukuvinkkienKasittely.saveRecommendation(lv);

    assertTrue(lukuvinkkienKasittely.getAllRecommendations().contains(lv));
  }

  @Test
  public void lukuvinkkienKasittelyReturnsAllAddedLukuvinkit() {
    LukuvinkkienKasittely lukuvinkkienKasittely = new LukuvinkkienKasittely();

    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));
    lukuvinkkienKasittely.saveRecommendation(new Lukuvinkki(""));

    assertEquals(4, lukuvinkkienKasittely.getAllRecommendations().size());
  }
}
