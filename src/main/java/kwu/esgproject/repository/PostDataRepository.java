package kwu.esgproject.repository;

import kwu.esgproject.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDataRepository extends JpaRepository<Post, Long>, PostDataRepositoryCustom{

}
