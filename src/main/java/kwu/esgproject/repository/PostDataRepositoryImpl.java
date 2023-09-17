package kwu.esgproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.QPost;
import kwu.esgproject.dto.PostListDto;
import kwu.esgproject.dto.QPostListDto;

import javax.persistence.EntityManager;
import java.util.List;

import static kwu.esgproject.domain.QPost.post;

public class PostDataRepositoryImpl implements PostDataRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PostDataRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<PostListDto> searchByInterest(Interest interest) {
        return queryFactory
                .select(new QPostListDto(
                        post.user.nickname,
                        post.title,
                        post.detail,
                        post.tags,
                        post.views,
                        post.likes,
                        post.share,
                        post.commentList.size(),
                        post.post_time
                ))
                .from(post)
                .where(post.interest.eq(interest))
                .fetch();
    }
}
