package kwu.esgproject.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Interest interest;

    @ElementCollection
    @CollectionTable(name = "news_category", joinColumns = @JoinColumn(name = "news_id"))
    private List<String> category = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String content;
    private int likes;
    private int unlikes;

    private LocalDateTime news_time;

    //==비지니스 로직==//
    public void addLike(){
        this.likes++;
    }
    public void addUnlike(){
        this.unlikes++;
    }

    //==연관관계 메서드==//
    public void setCompany(Company company){
        this.company = company;
        company.getNewsList().add(this);
    }

    //==생성 메서드==//
    public static News createNews(String title, Company company, String content, Interest interest){
        News news = new News();
        news.setTitle(title);
        news.setCompany(company);
        news.setContent(content);
        news.setLikes(0);
        news.setUnlikes(0);
        news.setInterest(interest);
        news.setNews_time(LocalDateTime.now());

        return news;
    }
}
