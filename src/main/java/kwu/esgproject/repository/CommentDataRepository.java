package kwu.esgproject.repository;

import kwu.esgproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentDataRepository extends JpaRepository<Comment, Long> {
}
