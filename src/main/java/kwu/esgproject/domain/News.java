package kwu.esgproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Category.class)
    private List<Category> category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String detail;
    private int likes;
    private int unlikes;

    private LocalDateTime news_time;
}
