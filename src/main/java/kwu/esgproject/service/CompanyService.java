package kwu.esgproject.service;

import kwu.esgproject.domain.Company;
import kwu.esgproject.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Long registration(Company company){
        companyRepository.save(company);
        return company.getId();
    }

    public Company findCompany(Long companyId){
        return companyRepository.findOne(companyId);
    }

    public List<Company> findAllCompany(){
        return companyRepository.findAll();
    }

    public Company findByName(String name){
        return companyRepository.findByName(name);
    }

    public List<Company> recommendCompanyByOneTag(String tag){
        return companyRepository.recommendByOneTag(tag);
    }

}
