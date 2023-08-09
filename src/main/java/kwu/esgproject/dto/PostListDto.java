package kwu.esgproject.dto;

import kwu.esgproject.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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
}
