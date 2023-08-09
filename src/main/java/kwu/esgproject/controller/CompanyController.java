package kwu.esgproject.controller;

import kwu.esgproject.domain.Company;
import kwu.esgproject.dto.CompanyDto;
import kwu.esgproject.dto.CompanyListDto;
import kwu.esgproject.dto.CreateCompanyRequest;
import kwu.esgproject.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/company/list")
    @ResponseBody
    public Result companyList() {
        List<Company> companyList = companyService.findAllCompany();
        List<CompanyListDto> collect = companyList.stream()
                .map(c -> new CompanyListDto(c.getName(), c.getTags(), c.getLocation(), c.getStock(), c.getTotal_donation()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @GetMapping("/company/{id}")
    @ResponseBody
    public CompanyDto companyDetail(@PathVariable("id") Long companyId) {
        Company findCompany = companyService.findCompany(companyId);
        return new CompanyDto(findCompany.getName(), findCompany.getTags(), findCompany.getLocation(), findCompany.getStock(), findCompany.getTotal_donation(), findCompany.getDonateList(), findCompany.getNewsList());
    }

    @PostMapping("/company/registration")
    public CreateCompanyResponse registrationCompany(@RequestBody @Valid CreateCompanyRequest request){
        Company company = Company.createCompany(request.getName(), request.getLocation(), request.getStock());
        for (String tag : request.getTags()) {
            company.addTag(tag);
        }

        Long companyId = companyService.registration(company);

        return new CreateCompanyResponse(companyId);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T company;
    }



    @Data
    static class CreateCompanyResponse {
        private Long id;

        public CreateCompanyResponse(Long id) {
            this.id = id;
        }
    }
}
