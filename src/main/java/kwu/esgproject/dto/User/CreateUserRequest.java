package kwu.esgproject.dto.User;

import kwu.esgproject.domain.Interest;
import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String nickname;
    private String birth_date;
    private String email;
    private String password;
    private Interest interest;
}
