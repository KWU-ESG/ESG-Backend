package kwu.esgproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Tag.class)
    private List<Tag> tags;

    private String location;
    private int stock;
    private int total_donation;

    @OneToMany(mappedBy = "company")
    private List<Donate> donateList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<News> newsList = new ArrayList<>();
}