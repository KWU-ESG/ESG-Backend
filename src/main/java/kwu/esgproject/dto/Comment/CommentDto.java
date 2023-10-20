package kwu.esgproject.dto.Comment;

import kwu.esgproject.domain.Post;
import kwu.esgproject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private User user;

    private Post post;
    private String detail; //내용
    private int likes;  // 좋아요 표시
    // 근데 누가 좋아요 눌렀는지는 모름 -> 아직 미정

    private LocalDateTime comment_time;
}
