package kwu.esgproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;

import javax.persistence.EntityManager;
import java.util.List;

import static kwu.esgproject.domain.QNews.news;

public class NewsRepositoryImpl implements NewsRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NewsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<News> searchByTitle(String title) {
        return queryFactory
                .selectFrom(news)
                .where(news.title.contains(title))
                .fetch();
    }

    @Override
    public List<News> searchByContent(String content) {
        return queryFactory
                .selectFrom(news)
                .where(news.content.contains(content))
                .fetch();
    }

    @Override
    public List<News> searchByInterest(Interest interest) {
        return queryFactory
                .selectFrom(news)
                .where(news.interest.eq(interest))
                .fetch();
    }
}
