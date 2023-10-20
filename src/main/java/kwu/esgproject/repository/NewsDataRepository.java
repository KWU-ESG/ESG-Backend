package kwu.esgproject.repository;

import kwu.esgproject.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDataRepository extends JpaRepository<News, Long>, NewsDataRepositoryCustom {

}
