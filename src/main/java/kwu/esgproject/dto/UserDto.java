package kwu.esgproject.dto;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private Long id;

    private String name;

    private String nickname;

    private String birthday;

    private String email;

    private String password;

    private Interest interest;


    public static UserDto touserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setNickname(user.getNickname());
        userDto.setBirthday(user.getBirth_date());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setInterest(user.getInterest());
        return userDto;
    }
}

