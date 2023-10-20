package kwu.esgproject.repository;

import kwu.esgproject.domain.Donate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateRepository extends JpaRepository<Donate, Long>, DonateRepositoryCustom {
}
