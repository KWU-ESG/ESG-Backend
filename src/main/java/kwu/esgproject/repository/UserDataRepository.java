package kwu.esgproject.repository;

import kwu.esgproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends JpaRepository<User, Long> {
    List<User> findUsersByName(String name);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByNickname(String nickname);

    List<User> findUsersByNameAndBirthDate(String name, String birthDate);
}
