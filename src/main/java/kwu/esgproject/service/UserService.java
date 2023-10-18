package kwu.esgproject.service;

import kwu.esgproject.auth.JwtService;
import kwu.esgproject.auth.JwtTokenProvider;
import kwu.esgproject.domain.Interest;
import kwu.esgproject.domain.Token;
import kwu.esgproject.domain.User;
import kwu.esgproject.dto.User.LoginUserRequest;
import kwu.esgproject.dto.User.UserDeleteDto;
import kwu.esgproject.repository.UserRepository;
import kwu.esgproject.repository.init.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    @Transactional
    public Long joinUser(String name, String nickname, String birth, String email, String password, Interest interest) {
        validateDuplicateUser(nickname, email);
        User user = User.createUser(name, nickname, birth, email, password, interest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userRepository.save(user);
            
        return user.getId();
    }
    
    @Transactional
    public Long joinAdmin(String name, String nickname, String birth, String email, String password, Interest interest) {
        validateDuplicateUser(nickname, email);
        User user = User.createAdmin(name, nickname, birth, email, password);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return user.getId();
    }
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user.getNickname(), user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return user.getId();
    }

    private void validateDuplicateUser(String nickname, String email) {
        Optional<User> checkUserNick = userRepository.findUserByNickname(nickname);
        Optional<User> checkUserEmail = userRepository.findUserByEmail(email);

        if(checkUserNick.isPresent()){
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        } else if (checkUserEmail.isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
    }


    // 로그인
    public ResponseEntity<?> login(LoginUserRequest loginUserRequest) {
        User user = userRepository.findUserByEmail(loginUserRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        // 비밀번호를 비교하여 로그인을 검증합니다.
        if (passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            Token tokenDto = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRoles());
            jwtService.login(tokenDto);

            // 로그인 성공 JSON 응답
            Map<String, String> response = new HashMap<>();
            response.put("message", "로그인 성공");
            response.put("access_token", tokenDto.getAccessToken());

            return ResponseEntity.ok(response);
        } else {
            // 로그인 실패 JSON 응답
            Map<String, String> response = new HashMap<>();
            response.put("message", "비밀번호가 일치하지 않습니다.");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 회원정보 수정

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}