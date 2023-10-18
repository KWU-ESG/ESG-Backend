package kwu.esgproject.service;

import kwu.esgproject.domain.Company;
import kwu.esgproject.domain.Donate;
import kwu.esgproject.domain.User;
import kwu.esgproject.repository.CompanyRepository;
import kwu.esgproject.repository.DonateRepository;
import kwu.esgproject.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final DonateRepository donateRepository;

    public Long donation(Donate donate){
        donateRepository.save(donate);
        return donate.getId();
    }

    public Donate findDonate(Long donateId){
        return donateRepository.findById(donateId).orElseThrow();
    }

    public List<Donate> findAllDonate(){
        return donateRepository.findAll();
    }

    public List<Donate> findByCompany(Long companyId){
        Company company = companyRepository.findById(companyId).get();
        return donateRepository.findDonatesByCompanyId(company.getId());
    }

    public List<Donate> findByUser(Long userId){
        User user = userRepository.findById(userId).get();
        return donateRepository.findDonatesByUserId(user.getId());
    }

    public List<Donate> findByCompanyWithUser(Long companyId, Long userId){
        Company company = companyRepository.findById(companyId).get();
        User user = userRepository.findById(userId).get();
        return donateRepository.findDonatesByCompanyIdAndUserId(company.getId(), user.getId());
    }
}
