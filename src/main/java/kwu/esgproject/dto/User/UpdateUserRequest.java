package kwu.esgproject.dto.User;
import kwu.esgproject.domain.Interest;
import lombok.Data;

@Data
public class UpdateUserRequest {
    String nickname;
    String email;
    String password;

    Interest interest;
}
