package kwu.esgproject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -802829204L;

    public static final QUser user = new QUser("user");

    public final StringPath birthDate = createString("birthDate");

    public final ListPath<Comment, QComment> commentList = this.<Comment, QComment>createList("commentList", Comment.class, QComment.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> create_time = createDateTime("create_time", java.time.LocalDateTime.class);

    public final ListPath<Donate, QDonate> donateList = this.<Donate, QDonate>createList("donateList", Donate.class, QDonate.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final EnumPath<Grade> grade = createEnum("grade", Grade.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Interest> interest = createEnum("interest", Interest.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<Post, QPost> postList = this.<Post, QPost>createList("postList", Post.class, QPost.class, PathInits.DIRECT2);

    public final ListPath<String, StringPath> prefer_tag = this.<String, StringPath>createList("prefer_tag", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> total_donation = createNumber("total_donation", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

