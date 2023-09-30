package kwu.esgproject.repository.init;

import  kwu.esgproject.domain.User;
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

    public void delete(User user) { em.remove(user);}
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

    public List<User> findListByEmail(String email){ // Login 에서 검사할 때 single result로 반환 할 경우 심각한 오류가 나옴
        return  em.createQuery("select u from User u where u.email =: email",User.class)
                .setParameter("email",email)
                .getResultList();
    }


    public List<User> findListByNickname(String nickname){
        return em.createQuery("select u from User u where u.nickname =: nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public User findByNameWithBirthDate(String name, String birthDate,String nickname) {
        return em.createQuery("select u from User u where u.name =: name " +
                        "and u.birthDate =:birthDate and u.nickname=:nickname", User.class)
                .setParameter("name", name)
                .setParameter("birthDate", birthDate)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }
  
    public List<User> findListByNameWithBirthDate(String name, String birthDate,String nickname) {
        return em.createQuery("select u from User u where u.name =: name " +
                        "and u.birthDate =:birthDate and u.nickname=:nickname", User.class)
                .setParameter("name",name)
                .setParameter("birthDate",birthDate)
                .setParameter("nickname",nickname)
                .getResultList();
    }
  
    public User findByNickname(String nickname){
        return em.createQuery("select u from User u where u.nickname =: nickname", User.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }
}
