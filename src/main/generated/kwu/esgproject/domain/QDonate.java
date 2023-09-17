package kwu.esgproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDonate is a Querydsl query type for Donate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDonate extends EntityPathBase<Donate> {

    private static final long serialVersionUID = 1085114064L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDonate donate = new QDonate("donate");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final QCompany company;

    public final DateTimePath<java.time.LocalDateTime> donate_time = createDateTime("donate_time", java.time.LocalDateTime.class);

    public final EnumPath<DonateStatus> donatestatus = createEnum("donatestatus", DonateStatus.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QDonate(String variable) {
        this(Donate.class, forVariable(variable), INITS);
    }

    public QDonate(Path<? extends Donate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDonate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDonate(PathMetadata metadata, PathInits inits) {
        this(Donate.class, metadata, inits);
    }

    public QDonate(Class<? extends Donate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

