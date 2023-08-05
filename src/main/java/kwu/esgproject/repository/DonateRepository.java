package kwu.esgproject.repository;

import kwu.esgproject.domain.Donate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

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

    // 전체 기부 조회


    // 회사에 대한 총 기부금 조회 -> 필요 없을 듯?
//    public List<Donate> findWithCompany


    // 회사에 대한 기부금과 기부한 사람 1대 1 조회



}
