package kwu.esgproject.repository;

import kwu.esgproject.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long>, NewsRepositoryCustom {
    List<News> findByCompanyId(Long id);
}
