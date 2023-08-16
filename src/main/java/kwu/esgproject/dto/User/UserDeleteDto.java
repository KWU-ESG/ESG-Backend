package kwu.esgproject.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDeleteDto {


    private String name;
    private String nickname;
    private String email;
    private String password;
}
