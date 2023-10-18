package kwu.esgproject.repository.init;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostJpaRepository {
    private final EntityManager em;

    public void save(Post post){ // Post 영속성 컨텍스트에 올림
        em.persist(post);
    }

    public void remove(Post post) {em.remove(post);}

    public Post findOne(Long id){
        return em.find(Post.class,id);
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p",Post.class)
                .getResultList();
    }

//    public List<PostSimpleQueryDto> findOrderDtos(){ // User와 comment
//        return em.createQuery("select new kwu.esgproject.repository.init.PostSimpleQueryDto(new p.id,p.detail,p.views,p.likes,p.share,p.open) from Post p " +
//                        "join p.user u join p.commentList cml ")
//                .getResultList();
//
//    }

    public List<Post> findById(Long id) {
        return em.createQuery("select p from Post p where p.id =: id", Post.class)
                .getResultList();
    }

    public List<Post> findByInterest(Interest interest){
        return em.createQuery("select p from Post p " +
                "where p.interest = :interest")
                .setParameter("interest", interest)
                .getResultList();
    }

//    public List<> findTags(Long id) {
//        return em.createQuery("select p.tags from Post p where p.id =: id")
//                .getResultList();
//    }
}
