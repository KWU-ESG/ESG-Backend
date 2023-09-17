package kwu.esgproject.service;

import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.User;
import kwu.esgproject.dto.User.LoginUserRequest;
import kwu.esgproject.dto.User.UserDeleteDto;
import kwu.esgproject.repository.UserDataRepository;
import kwu.esgproject.repository.init.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;

    // 회원 가입
    @Transactional
    public Long join(User user) {
        // 아이디 중복 체크 validate써서
        try {
            validateDuplicateJoinUserEmail(user);  // singleResult 를 사용해서 data를 가져오게 될때 DB에 값이 없다면
            // .EmptyResultDataAccessException: No entity found for query; nested exception is javax.persistence.NoResultException: No entity found for query
            // 이런 오류가 나오고 그렇기 때문에 try catch를 통해서 관리해줘야한다.
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            validateDuplicateJoinUserNickname(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        // 저장
        userDataRepository.save(user);
        return user.getId();
    }
    private void validateDuplicateJoinUserEmail(User user) {
        // ID로 중복체크
        Optional<User> userByEmail = userDataRepository.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new IllegalStateException("존재하는 이메일 입니다. ");
        }
    }
    private void validateDuplicateJoinUserNickname(User user) {
        //nickname 중복체크
        Optional<User> userByNickname = userDataRepository.findUserByNickname(user.getNickname());
        if(userByNickname.isPresent()){
            throw new IllegalStateException("존재하는 닉네임 입니다. ");
        }
    }

    // 로그인
    @Transactional
    public String Login(LoginUserRequest loginUserRequest) {
        try{
            String nickname = validateLoginUser(loginUserRequest);
            return nickname ; //userRepository.findByEmail(loginUserRequest.getEmail()).getNickname();
        }catch (Exception e){
            System.out.println(e);
            return "";
        }
    }
    private String validateLoginUser(LoginUserRequest loginUserRequest) {
        // email을 통해 1차적으로 있는지 확인
        Optional<User> userByEmail = userDataRepository.findUserByEmail(loginUserRequest.getEmail());

        if(userByEmail.isEmpty()){
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }
        else {
            User user = userByEmail.get();
            // 같은지 확인
            if (!user.getPassword().equals(loginUserRequest.getPassword())) {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            } else {
                return user.getNickname();
            }
        }
    }

    // 회원정보 수정
    @Transactional // 어디까지 수정하게 할지?  이메일, 비밀번호 , 태그
    public void update(Long id, String nickname, String email, String password, Interest interest) {
        Optional<User> userById = userDataRepository.findById(id);
        User user = userById.get();
        try{
            validateDuplicateUpdateUserNickname(user,nickname);
            user.setNickname(nickname);
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            validateDuplicateUpdateUserEmail(user,email);
            user.setEmail(email);
        }catch (Exception e){
            System.out.println(e);
        }
        user.setPassword(password);
        user.setInterest(interest);

        // 나중에 Change 메서드로 변경??
    }
    private void validateDuplicateUpdateUserNickname(User user,String nickname) {
        //nickname 중복체크
        Optional<User> userByNickname = userDataRepository.findUserByNickname(nickname);

        if(userByNickname.isEmpty()){
            if(user.getNickname().equals(nickname)){
                throw new IllegalStateException("동일한 닉네임입니다");
            }
        }
        else {
            throw new IllegalStateException("존재하는 닉네임 입니다.");
        }
    }
    private void validateDuplicateUpdateUserEmail(User user, String email){
        Optional<User> userByEmail = userDataRepository.findUserByEmail(email);

        if(userByEmail.isEmpty()){
            if(user.getNickname().equals(email)){
                throw new IllegalStateException("동일한 이메일입니다");
            }
        }
        else {
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    @Transactional
    public UserDeleteDto deleteUser(User user,String password) {
        try {
            validateDeleteUser(user, password);
            UserDeleteDto userDeleteDto =
                    new UserDeleteDto(user.getName(), user.getNickname(),
                            user.getEmail(), user.getPassword()); //Dto 삭제된 정보 저장
            userRepository.delete(user);
            return userDeleteDto;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
  
    public void validateDeleteUser(User user,String password) {
        User finduser = userRepository.findOne(user.getId());
        if (!finduser.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
    }



    @Transactional
    public User SearchUserId(String name, String birth_date, String nickname) {
        try {
            validateSearchIdUser(name,birth_date,nickname);
            return userRepository.findByNameWithBirthDate(name, birth_date, nickname);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private void validateSearchIdUser(String name, String birth_date, String nickname) {
        List<User> users = userRepository.findListByNameWithBirthDate(name, birth_date, nickname);  // singleResult
        if (users.isEmpty()) {
            throw new IllegalStateException("올바른 정보를 입력하시오");
        }
    }

    @Transactional
    public String SearchUserPw(String email) {
        try {
            validateSearchPwUser(email);
            User user = userRepository.findByEmail(email);
            AfterValidateChangePw(user);
            return user.getPassword();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    // 문자열 파싱해서 나중에 생일과 이것저것 조합해서 넣어주기
    public void AfterValidateChangePw(User user) {
        user.setPassword("1234");
    }

    public void validateSearchPwUser(String email) {
        List<User> findUser = userRepository.findListByEmail(email); // singleResult
        if (findUser.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 이메일입니다");
        }

    }

    @Transactional
    public void withdrawal(Long id) {
        User user = userRepository.findOne(id);

        user.withdrawal();
    }

    //전체 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 하나 아이디로 조회
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }

    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

//    public String findByNameAndBirthDateAndNickname(String name, String birth_date, String nickname){
//        return userRepository.findByNameWithBirthDate(name,birth_date,nickname);
//    }

}