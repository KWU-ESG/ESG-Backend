package kwu.esgproject.service;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Donate;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.CompanyDataRepository;
import kwu.esgproject.repository.DonateDataRepository;
import kwu.esgproject.repository.UserDataRepository;
import kwu.esgproject.repository.init.CompanyRepository;
import kwu.esgproject.repository.init.DonateRepository;
import kwu.esgproject.repository.init.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DonationService {
//    private final DonateRepository donateRepository;
//    private final CompanyRepository companyRepository;
//    private final UserRepository userRepository;

    private final UserDataRepository userDataRepository;
    private final CompanyDataRepository companyDataRepository;
    private final DonateDataRepository donateDataRepository;

    public Long donation(Donate donate){
        donateDataRepository.save(donate);
        return donate.getId();
    }

    public Donate findDonate(Long donateId){
        return donateDataRepository.findById(donateId).orElseThrow();
    }

    public List<Donate> findAllDonate(){
        return donateDataRepository.findAll();
    }

    public List<Donate> findByCompany(Long companyId){
        Company company = companyDataRepository.findById(companyId).get();
        return donateDataRepository.findDonatesByCompanyId(company.getId());
    }

    public List<Donate> findByUser(Long userId){
        User user = userDataRepository.findById(userId).get();
        return donateDataRepository.findDonatesByUserId(user.getId());
    }

    public List<Donate> findByCompanyWithUser(Long companyId, Long userId){
        Company company = companyDataRepository.findById(companyId).get();
        User user = userDataRepository.findById(userId).get();
        return donateDataRepository.findDonatesByCompanyIdAndUserId(company.getId(), user.getId());
    }
}
