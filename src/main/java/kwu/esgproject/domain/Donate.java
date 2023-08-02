package kwu.esgproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Donate {
    @Id @GeneratedValue
    @Column(name = "donate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private int amount;

    private LocalDateTime donate_time;

    //==연관관계 메서드==//
    public void setUser(User user){
        this.user = user;
        user.getDonateList().add(this);
    }

    public void setCompany(Company company){
        this.company = company;
        company.getDonateList().add(this);
    }

    //==생성 메서드==//
    public static Donate createDonate(User user, Company company, int amount){
        Donate donate = new Donate();
        donate.setUser(user);
        donate.setCompany(company);
        donate.setAmount(amount);

        return donate;
    }
}
