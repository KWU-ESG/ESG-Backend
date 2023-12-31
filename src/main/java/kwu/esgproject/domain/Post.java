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

    private String title;

    private String detail;

    @Enumerated(EnumType.STRING)
    private Interest interest;

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
    // 공개인지 비공개인지 권환에 따라 ADMIN일 경우 상관없이 조회가능 User일 경우 close이면 조회 불가
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
  
    // 좋아요 취소, 공유 취소?
    public void cancelLike(){
        this.likes--;
    }
    public void cancelShare(){
        this.share--;
    }
    // tag 추가

    // tag 취소

    public void setClose(){
        this.open = Open.CLOSED;
    }

    public void setOpen(){
        this.open = Open.OPEN;
    }

    //==연관관계 메서드==//
    public void setUser(User user){
        this.user = user;
        user.getPostList().add(this);
    }

    //==생성 메서드==//

    public static Post createPost(User user, String title, String detail) {
        Post post = new Post();
        post.setUser(user);
        post.setTitle(title);
        post.setDetail(detail);
        post.setViews(0);
        post.setLikes(0);
        post.setShare(0);
        post.setOpen(Open.OPEN);
        post.setPost_time(LocalDateTime.now());

        // tags 필드 초기화
        post.setTags(new ArrayList<>());

        return post;
    }


    public void addTag(String tag){
        this.getTags().add(tag);
    }
}
