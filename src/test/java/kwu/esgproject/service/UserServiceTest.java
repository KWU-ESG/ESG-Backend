package kwu.esgproject.service;

import kwu.esgproject.domain.User;
import kwu.esgproject.dto.User.LoginUserRequest;
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







    }





}