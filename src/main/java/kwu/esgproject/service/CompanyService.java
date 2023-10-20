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
//    private final CompanyRepository companyRepository;
    private final CompanyRepository companyRepository;

    public Long registration(Company company){
        companyRepository.save(company);
        return company.getId();
    }

    public Company findCompany(Long companyId){
        return companyRepository.findById(companyId).orElseThrow();
    }

    public List<Company> findAllCompany(){
        return companyRepository.findAll();
    }

    public Company findByName(String name){
        return companyRepository.findCompanyByName(name).orElseThrow();
    }

//    public List<Company> recommendCompanyByOneTag(String tag){
//        return companyDataRepository.recommendByOneTag(tag);
//    }

    @Transactional
    public void editCompanyDetail(Long companyId, String name, List<String> tags, String description, String location, int stock){
        Company company = companyRepository.findById(companyId).get();
        company.setName(name);
        company.setTags(tags);
        company.setDescription(description);
        company.setLocation(location);
        company.setStock(stock);
    }

    public void deleteCompany(Long companyId){
        Company company = companyRepository.findById(companyId).get();
        companyRepository.delete(company);
    }

}
