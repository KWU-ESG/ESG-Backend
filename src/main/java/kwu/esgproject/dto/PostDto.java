package kwu.esgproject.dto;

import kwu.esgproject.domain.Comment;
import kwu.esgproject.domain.Open;
import kwu.esgproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDto {
    private String user_nickname;

    private String title;
    private String detail;

    private List<String> tags;

    private int views;
    private int likes;
    private int share;
    private int comment;

    private List<Comment> commentList = new ArrayList<>();

    private LocalDateTime post_time;
}
