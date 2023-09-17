package kwu.esgproject.repository;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Donate;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.init.DonateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DonateDataRepositoryTest {
    @Autowired
    EntityManager em;

    @Autowired
    DonateDataRepository donateDataRepository;
    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    CompanyDataRepository companyDataRepository;

    @Autowired
    DonateRepository donateRepository;

    @Test
    public void searchByUserId() throws Exception {
        List<User> all = userDataRepository.findAll();
        List<Donate> donatesByUser = donateDataRepository.findDonatesByUserId(all.get(2).getId());
        for (Donate donate : donatesByUser) {
            System.out.println("donate = " + donate);
        }
    }

    @Test
    public void searchByCompanyId() throws Exception {
        List<Company> all = companyDataRepository.findAll();
        List<Donate> donatesByCompanyId = donateDataRepository.findDonatesByCompanyId(all.get(1).getId());
        for (Donate donate : donatesByCompanyId) {
            System.out.println("donate1 = " + donate);
        }
        Assertions.assertThat(donatesByCompanyId.size()).isEqualTo(4);
    }

    @Test
    public void searchByUserAndCompany() throws Exception {
        List<User> users = userDataRepository.findAll();
        List<Company> companies = companyDataRepository.findAll();
        List<Donate> result = donateDataRepository.findDonatesByCompanyIdAndUserId(companies.get(1).getId(), users.get(2).getId());

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}