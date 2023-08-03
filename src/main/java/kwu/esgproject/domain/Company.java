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

    private String name;

    @ElementCollection
    @CollectionTable(name = "company_tags", joinColumns = @JoinColumn(name = "company_id"))
    private List<String> tags = new ArrayList<>();

    private String location; // api 써서 location 찾기
    private int stock; // api 써서 주식 데이터 불러오기 ?? 아마 조금더 복잡해질지도 ??
    private int total_donation;

    @OneToMany(mappedBy = "company")
    private List<Donate> donateList = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<News> newsList = new ArrayList<>();

    //==생성 메서드==//
    public static Company createCompany(String name, String location, int stock ,String ...tags){
        Company company = new Company();
        company.setName(name);
        company.setLocation(location);
        company.setStock(stock);
        for (String tag : tags) {
            company.getTags().add(tag);
        }

        return company;
    }

    //==조회 로직==//
    public void getTotalDonate(){
        int totalDonate = 0;
        for (Donate donate : donateList) {
            totalDonate += donate.getAmount();
        }

        this.total_donation = totalDonate;
    }
}
