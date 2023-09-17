package kwu.esgproject.repository;

import kwu.esgproject.domain.Donate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonateDataRepository extends JpaRepository<Donate, Long>, DonateDataRepositoryCustom {
}
