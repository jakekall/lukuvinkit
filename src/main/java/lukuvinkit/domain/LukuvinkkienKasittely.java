package lukuvinkit.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lukuvinkit.dao.BlogpostDao;
import lukuvinkit.dao.KirjaDao;
import lukuvinkit.dao.PodcastDao;
import lukuvinkit.dao.VideoDao;

public class LukuvinkkienKasittely implements LukuvinkkienKasittelyIF {

  private BlogpostDao blogpostDao;
  private KirjaDao kirjaDao;
  private PodcastDao podcastDao;
  private VideoDao videoDao;

  public LukuvinkkienKasittely(BlogpostDao blogpostDao, KirjaDao kirjaDao,
          PodcastDao podcastDao, VideoDao videoDao) {
    this.blogpostDao = blogpostDao;
    this.kirjaDao = kirjaDao;
    this.podcastDao = podcastDao;
    this.videoDao = videoDao;
  }

  @Override
  public void saveRecommendation(Lukuvinkki l) throws SQLException {
    if (l.getTyyppi() == LukuvinkkiTyyppi.BLOGPOST) {
      saveBlogpostRecommendation(l);
    } else if (l.getTyyppi() == LukuvinkkiTyyppi.KIRJA) {
      saveBookRecommendation(l);
    } else if (l.getTyyppi() == LukuvinkkiTyyppi.PODCAST) {
      savePodcastRecommendation(l);
    } else if (l.getTyyppi() == LukuvinkkiTyyppi.VIDEO) {
      saveVideRecommendation(l);
    }
  }

  @Override
  public void deleteRecommendation(Lukuvinkki l) throws SQLException {
    int id = l.getId();
    
    if (l.getTyyppi() == LukuvinkkiTyyppi.BLOGPOST) {
      blogpostDao.delete(id);
    } else if (l.getTyyppi() == LukuvinkkiTyyppi.KIRJA) {
     kirjaDao.delete(id);
    } else if (l.getTyyppi() == LukuvinkkiTyyppi.PODCAST) {
      podcastDao.delete(id);
    } else if (l.getTyyppi() == LukuvinkkiTyyppi.VIDEO) {
      videoDao.delete(id);
    }
  }

  @Override
  public void editRecommendation(Lukuvinkki l) {
  }

  @Override
  public ArrayList<Lukuvinkki> getAllRecommendations() throws SQLException {
    ArrayList<Lukuvinkki> allRecommendations = new ArrayList<>();

    List<Blogpost> blogpostRecommendations = blogpostDao.list();
    List<Kirja> bookRecommendations = kirjaDao.list();
    List<Podcast> podcastRecommendations = podcastDao.list();
    List<Video> videoRecommendations = videoDao.list();

    allRecommendations.addAll(blogpostRecommendations);
    allRecommendations.addAll(bookRecommendations);
    allRecommendations.addAll(podcastRecommendations);
    allRecommendations.addAll(videoRecommendations);

    return allRecommendations;
  }

  private void saveBlogpostRecommendation(Lukuvinkki l) throws SQLException {
    blogpostDao.create((Blogpost) l);
  }

  private void saveBookRecommendation(Lukuvinkki l) throws SQLException {
    kirjaDao.create((Kirja) l);
  }

  private void savePodcastRecommendation(Lukuvinkki l) throws SQLException {
    podcastDao.create((Podcast) l);
  }

  private void saveVideRecommendation(Lukuvinkki l) throws SQLException {
    videoDao.create((Video) l);
  }

}
