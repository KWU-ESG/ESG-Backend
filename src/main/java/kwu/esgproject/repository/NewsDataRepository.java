package kwu.esgproject.repository;

import kwu.esgproject.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsDataRepository extends JpaRepository<News, Long>, NewsDataRepositoryCustom {
    List<News> findByCompanyId(Long id);
}
