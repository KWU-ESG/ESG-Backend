package kwu.esgproject.dto.User;


import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRequest {
    String nickname;
    String email;
    String password;
    List<String> preferTags;
}
