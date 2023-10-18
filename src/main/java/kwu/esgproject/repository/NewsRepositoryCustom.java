package kwu.esgproject.repository;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;

import java.util.List;

public interface NewsRepositoryCustom {
    List<News> searchByTitle(String title);
    List<News> searchByContent(String content);
    List<News> searchByInterest(Interest interest);
}
