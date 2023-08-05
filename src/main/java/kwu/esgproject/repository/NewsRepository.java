package kwu.esgproject.repository;

import kwu.esgproject.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsRepository {
    private final EntityManager em;

    // News
    public void save(News news){
        em.persist(news);
    }

    public  News findOne(Long id){
        return em.find(News.class,id);
    }
    public List<News> findAll(){
        return em.createQuery("select n from News n ",News.class)
                .getResultList();
    }
    // news와 태그에 따른 검색?




}
