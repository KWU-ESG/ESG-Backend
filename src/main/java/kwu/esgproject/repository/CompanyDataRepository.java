package kwu.esgproject.repository;

import kwu.esgproject.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyDataRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompanyByName(String name);
}
