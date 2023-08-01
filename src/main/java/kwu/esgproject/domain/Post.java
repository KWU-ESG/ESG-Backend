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

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Tag.class)
    private List<Tag> tags = new ArrayList<>();

    private int views;
    private int likes;
    private int share;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    private LocalDateTime post_time;

    @Enumerated(EnumType.STRING)
    private Open open;
}
