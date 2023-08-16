package kwu.esgproject.dto.User;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {

    private String name;
    private String nickname;
    private String birth_date;
    private String email;
    private String password;

    private List<String> prefer_tag;



}
