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
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private LocalDateTime create_time;

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Donate> donateList = new ArrayList<>();

    private int total_donation;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Tag.class)
    private List<Tag> prefer_tag = new ArrayList<>();

}
