package kwu.esgproject.dto.User;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
