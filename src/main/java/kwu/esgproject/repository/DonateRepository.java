package kwu.esgproject.repository;

import kwu.esgproject.domain.Donate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class DonateRepository {
    private final EntityManager em;

    public void save(Donate donate){
        em.persist(donate);
    }

    public Donate findOne(Long id)
    {
        return em.find(Donate.class,id);
    }
    // Donate 총량 세는 것?


}
