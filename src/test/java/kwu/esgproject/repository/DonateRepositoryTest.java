package kwu.esgproject.repository;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Donate;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.init.DonateJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@SpringBootTest
@Transactional
class DonateRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    DonateRepository donateRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DonateJpaRepository donateJpaRepository;

    @Test
    public void searchByUserId() throws Exception {
        List<User> all = userRepository.findAll();
        List<Donate> donatesByUser = donateRepository.findDonatesByUserId(all.get(2).getId());
        for (Donate donate : donatesByUser) {
            System.out.println("donate = " + donate);
        }
    }

    @Test
    public void searchByCompanyId() throws Exception {
        List<Company> all = companyRepository.findAll();
        List<Donate> donatesByCompanyId = donateRepository.findDonatesByCompanyId(all.get(1).getId());
        for (Donate donate : donatesByCompanyId) {
            System.out.println("donate1 = " + donate);
        }
        Assertions.assertThat(donatesByCompanyId.size()).isEqualTo(4);
    }

    @Test
    public void searchByUserAndCompany() throws Exception {
        List<User> users = userRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        List<Donate> result = donateRepository.findDonatesByCompanyIdAndUserId(companies.get(1).getId(), users.get(2).getId());

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}