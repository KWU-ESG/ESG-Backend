package kwu.esgproject.service;

import kwu.esgproject.domain.User;
import kwu.esgproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public Long join(User user){
        // 아이디 중복 체크 validate써서
        validateDuplicateUser(user);
        // 저장
        userRepository.save(user);
        return user.getId();
    }

    // 이름 닉네임 생일 이메일
    private void validateDuplicateUser(User user) {
        // ID로 중복체크
        List<User> findUser = userRepository.findById(user.getId());
        if(!findUser.isEmpty()){
            throw new IllegalStateException("존재하는 아이디 입니다. ");
        }
    }

    // 회원정보 수정
    @Transactional // 어디까지 수정하게 할지?  이메일, 비밀번호 , 태그
    public void update(Long id, String email, String passward, String ...preferTags){
        User user = userRepository.findOne(id);
        user.setEmail(email);
        user.setPassword(passward);
        user.setPrefer_tag(Arrays.asList(preferTags)); // String [] -> List<String>

        // 나중에 Change 메서드로 변경??
    }

//    private void UserChange(String email, String passward, String[] tags) {
//
//    }

    // 회원 탈퇴
    @Transactional
    public void withdrawal(Long id){
        User user = userRepository.findOne(id);

        user.withdrawal();
    }

    //전체 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }
    // 하나 아이디로 조회
    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }




}
