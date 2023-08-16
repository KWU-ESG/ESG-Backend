package kwu.esgproject.repository;

import kwu.esgproject.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List; // java 18로 들어감

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user){ // user 영속성 저장
        em.persist(user);
    }

    public void delete(User user) { em.persist(user);}
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
    public List<User> findById(Long id){ // 아이디로 찾기
        return em.createQuery("select u from User u where  u.id = :id",User.class).
                setParameter("id",id)
                .getResultList();
    }

    public User findByEmail(String email) {
        return  em.createQuery("select u from User u where u.email =: email",User.class)
                .setParameter("email",email)
                .getSingleResult();
    }

    public User findByNameWithBirthDate(String name, String birthDate,String nickname) {
        return em.createQuery("select u from User u where u.name =: name " +
                        "and u.birth_date =:birthDate and u.nickname=:nickname", User.class)
                .getSingleResult();
    }
}
