package kwu.esgproject.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * kwu.esgproject.dto.QPostListDto is a Querydsl Projection type for PostListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostListDto extends ConstructorExpression<PostListDto> {

    private static final long serialVersionUID = 553079817L;

    public QPostListDto(com.querydsl.core.types.Expression<String> user_nickname, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> detail, com.querydsl.core.types.Expression<? extends java.util.List<String>> tags, com.querydsl.core.types.Expression<Integer> views, com.querydsl.core.types.Expression<Integer> likes, com.querydsl.core.types.Expression<Integer> share, com.querydsl.core.types.Expression<Integer> comment, com.querydsl.core.types.Expression<java.time.LocalDateTime> post_time) {
        super(PostListDto.class, new Class<?>[]{String.class, String.class, String.class, java.util.List.class, int.class, int.class, int.class, int.class, java.time.LocalDateTime.class}, user_nickname, title, detail, tags, views, likes, share, comment, post_time);
    }

}

