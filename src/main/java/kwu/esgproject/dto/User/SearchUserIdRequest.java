package kwu.esgproject.dto.User;


import lombok.Data;

@Data
public class SearchUserIdRequest {
    private String name;
    private String birth_date;

    private String nickname;

}
