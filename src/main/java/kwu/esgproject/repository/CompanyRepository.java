package kwu.esgproject.repository;

import kwu.esgproject.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {
    private final EntityManager em;

    public void save(Company company){
        em.persist(company);
    }


    public Company findOne(Long id){
        return em.find(Company.class,id);
    }
    public List<Company> findAll(){
        return em.createQuery("select c from Company c ",Company.class)
                .getResultList();
    }

    // 쿼리 추가할 것?





}
