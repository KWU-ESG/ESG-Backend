package kwu.esgproject.repository;

import kwu.esgproject.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List; // java 18로 들어감

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private EntityManager em;

    public void save(User user){ // user 영속성 저장
        em.persist(user);
    }

    public User findOne(Long id){ // id로 하나 조회
        return em.find(User.class,id);
    }
    public List<User> findAll(){ // 전부 조회
        return em.createQuery("select u from User u ",User.class)
                .getResultList();
    }

    public List<User> findByName(String name){ // 이름으로 찾기
        return em.createQuery("select u from User u where  u.name = :name",User.class).
                setParameter("name",name)
                .getResultList();
    }


}
