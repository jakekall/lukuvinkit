import java.util.ArrayList;

public interface LukuvinkkienKasittelyIF {
    void saveRecommendation(Lukuvinkki l);
    void deleteRecommendation(Lukuvinkki l);
    void editRecommendation(Lukuvinkki l);
    ArrayList getAllRecommendations();

}
