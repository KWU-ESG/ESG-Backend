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
<<<<<<< HEAD
    private Interest interest;
=======

    private List<String> prefer_tag;
>>>>>>> 2d7df2c12ec8b6ce813df5e697ebd8ab1e8dce94
}
