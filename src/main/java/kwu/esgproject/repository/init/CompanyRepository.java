package kwu.esgproject.repository.init;

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

    public Company findByName(String name){
        return em.createQuery("select c from Company c where c.name =:name ", Company.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    //원하는 태그 하나로 회사 추천
    public List<Company> recommendByOneTag(String tag){
        return em.createQuery("select c from Company c " +
                "where :tag MEMBER OF c.tags", Company.class)
                .setParameter("tag", tag)
                .getResultList();
    }

    public void delete(Company company){
        em.remove(company);
    }

}
