package kwu.esgproject.repository;

import kwu.esgproject.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NewRepository {
    private final EntityManager em;

    // News
    public void save(News news){
        em.persist(news);
    }





}
