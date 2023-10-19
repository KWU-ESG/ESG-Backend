package kwu.esgproject.dto.User;

import kwu.esgproject.domain.Interest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String nickname;
    private String birth;
    private String email;
    private String password;
    private Interest interest;
}
