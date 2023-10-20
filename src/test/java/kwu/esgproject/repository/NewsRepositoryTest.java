package kwu.esgproject.repository;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.News;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class NewsRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void searchByCompany() throws Exception {
        List<Company> companies = companyRepository.findAll();
        List<News> result = newsRepository.findByCompanyId(companies.get(1).getId());

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void searchByTitle() throws Exception {
        List<News> result = newsRepository.searchByTitle("뉴스");

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void searchByContent() throws Exception {
        List<News> result = newsRepository.searchByContent("재민이");

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void searchByInterest() throws Exception {
        List<News> result = newsRepository.searchByInterest(Interest.E);

        for (News news : result) {
            System.out.println("news = " + news);
        }

        assertThat(result.size()).isEqualTo(3);
    }

}