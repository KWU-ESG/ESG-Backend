package kwu.esgproject.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostListDto {
    private String user_nickname;

    private String title;
    private String detail;

    private List<String> tags;

    private int views;
    private int likes;
    private int share;
    private int comment;

    private LocalDateTime post_time;

    @QueryProjection
    public PostListDto(String user_nickname, String title, String detail, List<String> tags, int views, int likes, int share, int comment, LocalDateTime post_time) {
        this.user_nickname = user_nickname;
        this.title = title;
        this.detail = detail;
        this.tags = tags;
        this.views = views;
        this.likes = likes;
        this.share = share;
        this.comment = comment;
        this.post_time = post_time;
    }
}
