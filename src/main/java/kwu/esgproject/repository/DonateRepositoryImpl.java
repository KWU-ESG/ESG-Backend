package kwu.esgproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kwu.esgproject.domain.Donate;

import javax.persistence.EntityManager;
import java.util.List;

import static kwu.esgproject.domain.QDonate.donate;

public class DonateRepositoryImpl implements DonateRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DonateRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<Donate> findDonatesByCompanyId(Long companyId) {
        return queryFactory
                .selectFrom(donate)
                .where(donate.company.id.eq(companyId))
                .fetch();
    }

    @Override
    public List<Donate> findDonatesByUserId(Long userId) {
        return queryFactory
                .selectFrom(donate)
                .where(donate.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<Donate> findDonatesByCompanyIdAndUserId(Long companyId, Long userId) {
        return queryFactory
                .selectFrom(donate)
                .where(donate.company.id.eq(companyId), donate.user.id.eq(userId))
                .fetch();
    }
}
