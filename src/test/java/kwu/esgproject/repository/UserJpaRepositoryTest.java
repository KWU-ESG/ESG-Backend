package kwu.esgproject.repository;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.User;
import kwu.esgproject.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserJpaRepositoryTest {
    @Autowired private EntityManager em;
//    @Autowired private UserRepository userRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    @Test
    public void 회원가입 () throws Exception{
        User user1 = User.createUser("김동현","Kim","20000507","1234@gmail.com","1234", Interest.E);
        User user2 = User.createUser("메시","messi","20000508","messi@gamil.com","1234", Interest.S);
        User user3 = User.createUser("호날두","hohoho","20000509","hohoho@gamil.com","1234", Interest.G);
        User user4 = User.createUser("음바페","embape","20000510","embape@gamil.com","1234", Interest.E);
        User user5 = User.createUser("호우","howo1","20000511","howo1@gamil.com","1234", Interest.S);
        User user6 = User.createUser("호우","howo2","20000512","howo2@gamil.com","1234", Interest.G);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

        User testFindOne = userRepository.findById(user1.getId()).get();

        // 같은 유저인지 확인
        assertEquals(user1,testFindOne);

        // email로 유저 찾기
        User testFindEmail = userRepository.findUserByEmail(user1.getEmail()).get();
        assertEquals(user1,testFindEmail);


        // name 으로 유저 찾기 (중복 할 수 도 있다는 것)
        List<User> FindWithNameUserList = userRepository.findUsersByName(user5.getName());

        for (User users: FindWithNameUserList) {
            System.out.println(users.getName() + " "+ users.getNickname()+" "+users.getBirthDate()+" "+users.getEmail()+ " "+users.getPassword());
        }
  
        // 전체 유저확인
        List<User> userList = userRepository.findAll();

        for (User users : userList) {
            System.out.println(users.getName() + " "+ users.getNickname()+" "+users.getBirthDate()+" "+users.getEmail()+ " "+users.getPassword());
        }

        // 유저 삭제
        userRepository.delete(user1);

        // 유저 삭제후 정상 삭제라면 null
        Optional<User> userById = userRepository.findById(user1.getId());

        if(userById.isEmpty()){
            System.out.println("정상 삭제 테스트임");
        }
        else{
            System.out.println("정상 삭제 테스트가 아님");
        }
}
}