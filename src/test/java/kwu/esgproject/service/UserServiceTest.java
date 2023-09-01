package kwu.esgproject.service;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.User;
import kwu.esgproject.dto.User.LoginUserRequest;
import kwu.esgproject.dto.User.UserDeleteDto;
import kwu.esgproject.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTest {
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;
    @Autowired private EntityManager em;

    @Test
    public void 회원서비스() throws Exception{
        User user1 = User.createUser("김동현","kim","20000507","1234@gmail.com","0000", Interest.E);
        User user2 = User.createUser("메시","messi","20000508","messi@gamil.com","1234", Interest.S);
        User user3 = User.createUser("호날두","hohoho","20000509","hohoho@gamil.com","1234", Interest.G);
        User user4 = User.createUser("음바페","embape","20000510","embape@gamil.com","1234", Interest.E);
        User user5 = User.createUser("호우","howo1","20000511","howo1@gamil.com","1234", Interest.S);
        User user6 = User.createUser("호우","howo2","20000512","howo2@gamil.com","1234", Interest.G);

        userService.join(user1);
        userService.join(user2);
        userService.join(user3);
        userService.join(user4);
        userService.join(user5);
        userService.join(user6);


        //로그인
        LoginUserRequest login1 = new LoginUserRequest(user1.getEmail(), user1.getPassword());
        userService.Login(login1);
        //이메일 없음
        /*LoginUserRequest login2 = new LoginUserRequest("kang@naver.com", "1234");
        userService.Login(login2);*/
        //비밀번호 틀림
        /*LoginUserRequest login3 = new LoginUserRequest(user2.getEmail(), "1111");
        userService.Login(login3);*/

        //정보변경
        /*userService.update(user1.getId(), "dong", "kang@gamil.com", "0000", Interest.E);
        System.out.println(user1.getNickname() + " "+user1.getEmail() + " "+user1.getPassword()+ " " + user1.getInterest());*/
        //존재하는 이메일
        /*userService.update(user1.getId(), "dong", "messi@gamil.com", "0000", Interest.E);
        System.out.println(user1.getNickname() + " "+user1.getEmail() + " "+user1.getPassword()+ " " + user1.getInterest());*/

        //정보삭제
        /*UserDeleteDto userDeleteDto = userService.deleteUser(user1,"0000");
        User deleteUser1 = userService.findOne(user1.getId());
        if (deleteUser1 == null){
            System.out.println("정상삭제되엇음");
        }
        System.out.println(userDeleteDto.getEmail() + " " + userDeleteDto.getPassword() + " " + userDeleteDto.getNickname() + " " + userDeleteDto.getName());*/

        //비밀번호 틀림
        /*userService.deleteUser(user2,"1111");*/

        //이름 생일 닉네임 -> 이메일 찾기
        /*User SearchIdUser2 = userService.SearchUserId(user2.getName(),user2.getBirth_date(),user2.getNickname());
        System.out.println(SearchIdUser2.getNickname() + " " + SearchIdUser2.getEmail() + " " + SearchIdUser2.getBirth_date());
        User SearchIdWrongUser2 = userService.SearchUserId("메메시",user2.getBirth_date(),user2.getNickname());*/

        /*String New_password = userService.SearchUserPw(user1.getEmail());
        System.out.println(New_password);*/
    }
}