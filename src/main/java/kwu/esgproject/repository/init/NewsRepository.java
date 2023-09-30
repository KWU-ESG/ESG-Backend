package kwu.esgproject.repository.init;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;
import kwu.esgproject.domain.Post;
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
    public List<News> findByTitle(String title){
        return em.createQuery("select n from News n " +
                "where n.title LIKE :title")
                .setParameter("title", title)
                .getResultList();
    }

    public List<News> findByOneCategory(String category){
        return em.createQuery("select n from News n " +
                        "where :category MEMBER OF n.category", News.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<News> findByInterest(Interest interest){
        return em.createQuery("select n from News n " +
                        "where n.interest = :interest")
                .setParameter("interest", interest)
                .getResultList();
    }


}
