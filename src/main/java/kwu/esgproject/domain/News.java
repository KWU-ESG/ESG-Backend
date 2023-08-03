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
public class News {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "news_category", joinColumns = @JoinColumn(name = "news_id"))
    private List<String> category = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private String detail;
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
    public static News createNews(String name, Company company, String detail, String ...category){
        News news = new News();
        news.setName(name);
        news.setCompany(company);
        news.setDetail(detail);
        news.setLikes(0);
        news.setUnlikes(0);
        for (String c : category) {
            news.getCategory().add(c);
        }

        return news;
    }
}
