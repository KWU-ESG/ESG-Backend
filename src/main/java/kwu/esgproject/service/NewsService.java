package kwu.esgproject.service;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;
import kwu.esgproject.repository.NewsRepository;
import kwu.esgproject.repository.init.NewsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsJpaRepository newsJpaRepository;
    private final NewsRepository newsRepository;

    public Long registration(News news){
        newsRepository.save(news);
        return news.getId();
    }

    public News findNews(Long newsId){
        return newsRepository.findById(newsId).orElseThrow();
    }

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }

    public List<News> findByTitle(String title){
        return newsRepository.searchByTitle(title);
    }

    public List<News> findByContent(String content){
        return newsRepository.searchByContent(content);
    }

    public List<News> findByInterest(Interest interest){
        return newsRepository.searchByInterest(interest);
    }
}
