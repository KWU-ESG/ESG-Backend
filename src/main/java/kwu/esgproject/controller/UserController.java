package kwu.esgproject.controller;

import kwu.esgproject.dto.User.*;
import kwu.esgproject.domain.*;
import kwu.esgproject.dto.User.*;
import kwu.esgproject.service.UserService;
import kwu.esgproject.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public String CreateUser(@RequestBody CreateUserRequest request){
        return "OK";
    }

    /*@PostMapping("/user/login")
    public LoginUserResponse LoginUser(@RequestBody @Valid LoginUserRequest request){
        String nickname = userService.Login(request);
        return new LoginUserResponse(nickname);
    }


    @PutMapping("/user/update/{user_id}")
    public UpdateUserResponse UpdateUser(@PathVariable("user_id") Long userId ,@RequestBody @Valid UpdateUserRequest updateUserRequest)
    {
        User user = userService.findOne(userId);
        userService.update(user.getId(),updateUserRequest.getNickname(), updateUserRequest.getEmail(),updateUserRequest.getPassword(), updateUserRequest.getInterest());

        return new UpdateUserResponse(user.getName(),user.getNickname()
                ,user.getBirthDate(),user.getEmail(),user.getPassword(),user.getInterest());
    }*/

    @DeleteMapping("/user/delete/{user_id}")
    public DeleteUserResponse DeleteUser(@PathVariable("user_id") Long userId ,@RequestBody @Valid DeleteUserRequest deleteUserRequest)
    {
        User user = userService.findOne(userId);
        UserDeleteDto userDeleteDto = userService.deleteUser(user,deleteUserRequest.getPassword());

        return new DeleteUserResponse(userDeleteDto.getName(), userDeleteDto.getNickname(),
                userDeleteDto.getEmail(), userDeleteDto.getPassword());
    }

    @GetMapping("/user/info/{user_id}")
    public CheckUserInfoResponse CheckUserInfo (@PathVariable("user_id") Long userId)
    {
        User user = userService.findOne(userId);

        return new CheckUserInfoResponse(user.getId(),user.getName(), user.getNickname(), user.getBirthDate(), user.getEmail(), user.getPassword(),
                user.getGrade(), user.getCreate_time(),user.getPostList(),user.getCommentList(),user.getDonateList(),user.getTotal_donation(), user.getInterest());
    }

    @GetMapping("/user/id_search") // email 찾기 이름과 생년원일로 찾아보자
    public SearchUserIdResponse SearchId(@RequestBody @Valid SearchUserIdRequest request)
    {
        //String email = userService.findByNameAndBirthDateAndNickname(request.getName(), request.getBirth_date(), request.getNickname()); // 이렇게 하면 안된다.
        User user = userService.SearchUserId(request.getName(),request.getBirth_date(),request.getNickname());

        return new SearchUserIdResponse(user.getEmail());
    }

    /*@GetMapping("/user/password_search") // email로 찾기?  email로 임시 비번 발송 ?
    public SearchUserPwResponse SearchPw(@RequestBody @Valid SearchUserPwRequest request)
    {
        String userPw = userService.SearchUserPw(request.getEmail());

        return new SearchUserPwResponse(userPw);
    }*/


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

        private Interest interest;

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

        private Interest interest;

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

