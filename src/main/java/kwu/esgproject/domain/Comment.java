package kwu.esgproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String detail; //내용

    private int likes;  // 좋아요 표시
    // 근데 누가 좋아요 눌렀는지는 모름 -> 아직 미정

    private LocalDateTime comment_time;


    //==비지니스 로직==//
    public void addLike(){
        this.likes++;
    }

    //==연관관계 메서드==//
    public void setUser(User user){
        this.user = user;
        user.getCommentList().add(this);
    }

    public void setPost(Post post){
        this.post = post;
        post.getCommentList().add(this);
    }

    //==생성 메서드==//
    public static Comment createComment(User user, Post post, String detail){
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setDetail(detail);
        comment.setLikes(0);

        return comment;
    }
}
