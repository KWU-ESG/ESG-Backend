package kwu.esgproject.repository;

import kwu.esgproject.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostDataRepository extends JpaRepository<Post, Long>, PostDataRepositoryCustom{

}
