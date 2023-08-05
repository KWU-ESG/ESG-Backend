package kwu.esgproject.service;

import kwu.esgproject.domain.News;
import kwu.esgproject.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;

    public Long registration(News news){
        newsRepository.save(news);
        return news.getId();
    }

    public News findNews(Long newsId){
        return newsRepository.findOne(newsId);
    }

    public List<News> findAllNews(){
        return newsRepository.findAll();
    }

    public List<News> findByTitle(String title){
        return newsRepository.findByTitle(title);
    }

    public List<News> findByOneCategory(String category){
        return newsRepository.findByOneCategory(category);
    }
}
