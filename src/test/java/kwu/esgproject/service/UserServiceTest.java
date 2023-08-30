package kwu.esgproject.service;

import kwu.esgproject.domain.User;
import kwu.esgproject.dto.User.LoginUserRequest;
import kwu.esgproject.dto.User.UserDeleteDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTest {
    @Autowired UserService userService;

    @Test
    public void 회원서비스() throws Exception{
        User user1 = User.createUser("김동현","Kim","20000507","1234@gmail.com","1234");
        User user2 = User.createUser("메시","messi","20000508","messi@gamil.com","1234");
        User user3 = User.createUser("호날두","hohoho","20000509","hohoho@gamil.com","1234");
        User user4 = User.createUser("음바페","embape","20000510","embape@gamil.com","1234");
        User user5 = User.createUser("호우","howo1","20000511","howo1@gamil.com","1234");
        User user6 = User.createUser("호우","howo2","20000512","howo2@gamil.com","1234");

        userService.join(user1);
        userService.join(user2);
        userService.join(user3);
        userService.join(user4);
        userService.join(user5);
        userService.join(user6);

        LoginUserRequest login1 = new LoginUserRequest();
        login1.setEmail(user1.getEmail());
        login1.setPassword(user1.getPassword());
        String nickname1 = userService.Login(login1);
        System.out.println(nickname1);

        userService.update(user1.getId(),"KimKim","1234@gamil.com","5555","E");
        System.out.println(user1.getNickname() + " "+user1.getEmail() + " "+user1.getPassword()+ " " + user1.getPrefer_tag());

        UserDeleteDto userDeleteDto = userService.deleteUser(user1,"1234");
        System.out.println(userDeleteDto.getEmail()+" " + userDeleteDto.getPassword()+ " "+userDeleteDto.getNickname()+ " "+userDeleteDto.getName());
        User deleteUser1 = userService.findOne(user1.getId());
        if (deleteUser1 == null){
            System.out.println("정상삭제되엇음");
        }


        userService.deleteUser(user2,"1111");


        User SearchIdUser2 = userService.SearchUserId(user2.getName(),user2.getBirth_date(),user2.getNickname());
        System.out.println(SearchIdUser2.getNickname() + " " + SearchIdUser2.getEmail() + " " + SearchIdUser2.getBirth_date());


        User SearchIdWrongUser2 = userService.SearchUserId("메메시",user2.getBirth_date(),user2.getNickname());

        String New_password = userService.SearchUserPw(user1.getEmail());
        System.out.println(New_password);


    }





}