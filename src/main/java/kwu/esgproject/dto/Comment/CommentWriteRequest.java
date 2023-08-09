package kwu.esgproject.dto.Comment;


import lombok.Data;

@Data
public class CommentWriteRequest {
    private Long userId;
    private String detail;

}
