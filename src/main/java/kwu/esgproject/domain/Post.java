package kwu.esgproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String detail;

    @ElementCollection
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    private List<String> tags;

    private int views;
    private int likes;
    private int share;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    private LocalDateTime post_time;

    @Enumerated(EnumType.STRING)
    private Open open;

    //==비지니스 로직==//
    public void addView(){
        this.views++;
    }
    public void addLike(){
        this.likes++;
    }
    public void addShare(){
        this.share++;
    }

    //==연관관계 메서드==//
    public void setUser(User user){
        this.user = user;
        user.getPostList().add(this);
    }

    //==생성 메서드==//

    public static Post createPost(User user, String detail, String ...tags) {
        Post post = new Post();
        post.setUser(user);
        post.setDetail(detail);
        for (String tag : tags) {
            post.getTags().add(tag);
        }
        post.setViews(0);
        post.setLikes(0);
        post.setShare(0);
        post.setOpen(Open.OPEN);

        return post;
    }
}
