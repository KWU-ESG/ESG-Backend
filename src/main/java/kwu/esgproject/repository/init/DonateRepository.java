package kwu.esgproject.repository.init;

import kwu.esgproject.domain.Donate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
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

    public List<Donate> findAll(){
        return em.createQuery("select d from Donate d", Donate.class)
                .getResultList();
    }

    // 회사 기부금 조회
    public List<Donate> findByCompany(String companyName){
        return em.createQuery("select d from Donate d " +
                "where d.company.name =: companyName")
                .setParameter("companyName", companyName)
                .getResultList();
    }
    public List<Donate> findByCompanyId(Long companyId){
        return em.createQuery("select d from Donate d " +
                "where d.company.id =: companyId")
                .setParameter("companyId", companyId)
                .getResultList();
    }

    // 유저 기부금 조회
    public List<Donate> findByUser(String userName){
        return em.createQuery("select d from Donate d " +
                "where d.user.name =: userName")
                .setParameter("userName", userName)
                .getResultList();
    }

    //회사, 유저 1대1 기부 리스트
    public List<Donate> findByCompanyWithUser(String companyName, String userName){
        return em.createQuery("select d from Donate d " +
                "where d.company.name = :companyName " +
                "and d.user.name = :userName")
                .setParameter("companyName", companyName)
                .setParameter("userName", userName)
                .getResultList();
    }

}
