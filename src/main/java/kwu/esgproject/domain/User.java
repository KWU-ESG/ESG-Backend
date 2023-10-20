package kwu.esgproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")//h2 db는 user이 예약어라 table 오류 발생(오류해결)
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String nickname;
    private String birthDate;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Interest interest;

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

    @ElementCollection
    @CollectionTable(name = "user_tags", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> prefer_tag = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    //==생성 메서드==//
    public static User createUser(String name, String nickname, String birth, String email, String password, Interest interest) {
        User user = new User();
        user.setName(name);
        user.setNickname(nickname);
        user.setBirthDate(birth);
        user.setEmail(email);
        user.setPassword(password);
        user.setInterest(interest);
        user.setGrade(Grade.USER);
        user.setCreate_time(LocalDateTime.now());
        user.setRoles(Collections.singletonList("ROLE_USER"));

        return user;
    }

    public static User createAdmin(String name, String nickname, String birth, String email, String password){
        User user = new User();
        user.setName(name);
        user.setNickname(nickname);
        user.setBirthDate(birth);
        user.setEmail(email);
        user.setPassword(password);
        user.setGrade(Grade.ADMIN);
        user.setCreate_time(LocalDateTime.now());
        user.setRoles(Collections.singletonList("ROLE_ADMIN"));

        return user;
    }

    //==조회 로직==//
    public void getTotalDonate(){
        int totalDonate = 0;
        for (Donate donate : this.donateList) {
            totalDonate = this.total_donation + donate.getAmount();
        }
        this.total_donation = totalDonate;
    }

    public int withdrawal() {
        return this.total_donation;
    }
}
