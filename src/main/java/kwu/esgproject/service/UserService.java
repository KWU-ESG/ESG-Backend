package kwu.esgproject.service;

import kwu.esgproject.domain.User;
import kwu.esgproject.dto.User.LoginUserRequest;
import kwu.esgproject.dto.User.UserDeleteDto;
import kwu.esgproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        validateDuplicateJoinUser(user);
        // 저장
        userRepository.save(user);
        return user.getId();
    }

    // 이름 닉네임 생일 이메일
    private void validateDuplicateJoinUser(User user) {
        // ID로 중복체크
        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null){
            throw new IllegalStateException("존재하는 아이디 입니다. ");
        }
    }
    // 로그인
    @Transactional
    public Long Login(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByEmail(loginUserRequest.getEmail());
        validateLoginUser(user,loginUserRequest);

        return user.getId();
    }
    private void validateLoginUser(User user,LoginUserRequest loginUserRequest) {
        // email을 통해 1차적으로 있는지 확인
        if(user ==  null){
            throw new IllegalStateException("존재하는 아이디 입니다.");
        }
        // 같은지 확인
        if(!user.getPassword().equals(loginUserRequest.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
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


    // 회원 탈퇴
    @Transactional
    public UserDeleteDto deleteUser(User user){
       // User user = userRepository.findOne(id);
        validateDeleteUser(user);

        UserDeleteDto userDeleteDto =
                new UserDeleteDto(user.getName(),user.getNickname(),
                        user.getEmail(),user.getPassword());
        userRepository.delete(user);

        return userDeleteDto;
    }

    public void validateDeleteUser(User user)
    {
        User finduser = userRepository.findOne(user.getId());
        if(!finduser.getPassword().equals(user.getPassword()))
        {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }
    @Transactional
    public User SearchUserId(String name,String birth_date,String nickname )
    {
        User user = userRepository.findByNameWithBirthDate(name,birth_date,nickname);
        validateSearchIdUser(user);
        return user;
    }

    private void validateSearchIdUser(User user) {
        if(user == null)
        {
            throw new IllegalStateException("올바른 정보를 입력하시오");
        }

    }

    @Transactional
    public String SearchUserPw(String email)
    {
        User finduser = userRepository.findByEmail(email);
        validateSearchPwUser(finduser);

        AfterValidateChangePw(finduser);
        return finduser.getPassword();
    }

    // 문자열 파싱해서 나중에 생일과 이것저것 조합해서 넣어주기
    public void AfterValidateChangePw(User user)
    {
        user.setPassword("1234");
    }

    public void validateSearchPwUser(User user) {
        if(user == null)
        {
            throw new IllegalStateException("존재하지 않는 이메일입니다");
        }

    }



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

    public User findByEmail(String userEmail)
    {
        return userRepository.findByEmail(userEmail);
    }

//    public String findByNameAndBirthDateAndNickname(String name, String birth_date, String nickname){
//        return userRepository.findByNameWithBirthDate(name,birth_date,nickname);
//    }

}
