package kwu.esgproject.repository;

import kwu.esgproject.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public void save(Post post){ // Post 영속성 컨텍스트에 올림
        em.persist(post);
    }

    public List<PostSimpleQueryDto> findOrderDtos(Post post){ // User와 comment
        return em.createQuery("select new kwu.esgproject.repository.PostSimpleQueryDto(new(p.id,p.detail,p.views,p.likes,p.share,p.open)) from Post p " +
                        "join p.user u join p.commentList cml ")
                .getResultList();

    }



}
