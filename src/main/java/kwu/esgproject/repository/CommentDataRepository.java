package kwu.esgproject.repository;

import kwu.esgproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDataRepository extends JpaRepository<Comment, Long> {
}
