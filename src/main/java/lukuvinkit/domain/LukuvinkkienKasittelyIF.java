package lukuvinkit.domain;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LukuvinkkienKasittelyIF {

  void saveRecommendation(Lukuvinkki l) throws SQLException;

  void deleteRecommendation(Lukuvinkki l);

  void editRecommendation(Lukuvinkki l);

  ArrayList<Lukuvinkki> getAllRecommendations() throws SQLException;
}
