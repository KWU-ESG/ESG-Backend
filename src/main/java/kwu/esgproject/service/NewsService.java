package kwu.esgproject.service;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;
import kwu.esgproject.repository.NewsDataRepository;
import kwu.esgproject.repository.init.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsDataRepository newsDataRepository;

    public Long registration(News news){
        newsDataRepository.save(news);
        return news.getId();
    }

    public News findNews(Long newsId){
        return newsDataRepository.findById(newsId).orElseThrow();
    }

    public List<News> findAllNews(){
        return newsDataRepository.findAll();
    }

    public List<News> findByTitle(String title){
        return newsDataRepository.searchByTitle(title);
    }

    public List<News> findByContent(String content){
        return newsDataRepository.searchByContent(content);
    }

    public List<News> findByInterest(Interest interest){
        return newsDataRepository.searchByInterest(interest);
    }
}
