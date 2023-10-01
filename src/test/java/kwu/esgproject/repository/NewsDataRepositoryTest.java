package kwu.esgproject.repository;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NewsDataRepositoryTest {
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private CompanyDataRepository companyDataRepository;
    @Autowired
    private NewsDataRepository newsDataRepository;

    @Test
    public void searchByCompany() throws Exception {
        List<Company> companies = companyDataRepository.findAll();
        List<News> result = newsDataRepository.findByCompanyId(companies.get(1).getId());

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void searchByTitle() throws Exception {
        List<News> result = newsDataRepository.searchByTitle("뉴스");

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void searchByContent() throws Exception {
        List<News> result = newsDataRepository.searchByContent("재민이");

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void searchByInterest() throws Exception {
        List<News> result = newsDataRepository.searchByInterest(Interest.E);

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(3);
    }

}