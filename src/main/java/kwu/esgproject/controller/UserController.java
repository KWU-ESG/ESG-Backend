package kwu.esgproject.controller;

import kwu.esgproject.domain.*;
import kwu.esgproject.dto.User.*;
import kwu.esgproject.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public CreateUserResponse CreateUser(@RequestBody @Valid CreateUserRequest request){
        User user = userService.findByEmail(request.getEmail());
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }


    @PostMapping("/user/login")
    public LoginUserResponse LoginUser(@RequestBody @Valid LoginUserRequest request){
        String nickname = userService.Login(request);
        return new LoginUserResponse(nickname);
    }


    @PutMapping("/user/update/{user_id}")
    public UpdateUserResponse UpdateUser(@PathVariable("user_id") Long userId)
    {
            User user = userService.findOne(userId);
            userService.update(user.getId(),user.getEmail(), user.getPassword(),user.getPrefer_tag().toString());

            return new UpdateUserResponse(user.getName(),user.getNickname()
            ,user.getBirth_date(),user.getEmail(),user.getPassword(),user.getPrefer_tag());
    }

    @DeleteMapping("/user/delete/{user_id}")
    public DeleteUserResponse DeleteUser(@PathVariable("user_id") Long userId)
    {
            User user = userService.findOne(userId);
            UserDeleteDto userDeleteDto = userService.deleteUser(user);

            return new DeleteUserResponse(userDeleteDto.getName(), userDeleteDto.getNickname(),
                    userDeleteDto.getEmail(), userDeleteDto.getPassword());
    }

    @GetMapping("/user/info/{user_id}")
    public CheckUserInfoResponse CheckUserInfo (@PathVariable("user_id") Long userId)
    {
        User user = userService.findOne(userId);

        return new CheckUserInfoResponse(user.getId(),user.getName(), user.getNickname(), user.getBirth_date(), user.getEmail(), user.getPassword(), user.getGrade(),user.getCreate_time(),user.getPostList(),user.getCommentList(),user.getDonateList(),user.getTotal_donation(),user.getPrefer_tag());
    }

    @GetMapping("/user/id_search") // email 찾기 이름과 생년원일로 찾아보자
    public SearchUserIdResponse SearchId(@RequestBody @Valid SearchUserIdRequest request)
    {
        //String email = userService.findByNameAndBirthDateAndNickname(request.getName(), request.getBirth_date(), request.getNickname()); // 이렇게 하면 안된다.
        User user = userService.SearchUserId(request.getName(),request.getNickname(),request.getBirth_date());

        return new SearchUserIdResponse(user.getEmail());
    }

    // /user/id_search/view
//    @GetMapping("/user/id_search/view")
//    public

    @GetMapping("/user/password_search") // email로 찾기?  email로 임시 비번 발송 ?
    public SearchUserPwResponse SearchPw(@RequestBody @Valid SearchUserPwRequest request)
    {
        String userPw = userService.SearchUserPw(request.getEmail());

        return new SearchUserPwResponse(userPw);
    }
    // /user/new_password/view
//    @GetMapping("/user/id_search/view")
//    public


    @Data
    static class CreateUserResponse{
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }
    }
    @Data
    static class LoginUserResponse{
        private String nickname;

        public LoginUserResponse(String nickname) {
            this.nickname = nickname;
        }
    }

    @Data
    @AllArgsConstructor
    static class DeleteUserResponse{
        private String name;
        private String nickname;
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class UpdateUserResponse{

        private String name;
        private String nickname;
        private String birth_date;
        private String email;
        private String password;

        private List<String> prefer_tag;

    }


    // 어디까지 보여줄 것인가에 대해서 고민 ?
    @Data
    @AllArgsConstructor
    static class CheckUserInfoResponse{
        private Long id;

        private String name;
        private String nickname;
        private String birth_date;
        private String email;
        private String password;

        private Grade grade;

        private LocalDateTime create_time;

        private List<Post> postList = new ArrayList<>();

        private List<Comment> commentList = new ArrayList<>();

        private List<Donate> donateList = new ArrayList<>();

        private int total_donation;

        private List<String> prefer_tag;

    }

    @Data
    static class SearchUserIdResponse{
        private String email;

        public SearchUserIdResponse(String email) {
            this.email = email;
        }
    }

    @Data
    static class SearchUserPwResponse{
        private String password;

        public SearchUserPwResponse(String password) {
            this.password = password;
        }
    }
}
