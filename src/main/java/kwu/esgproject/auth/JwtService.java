package kwu.esgproject.auth;

import kwu.esgproject.domain.RefreshToken;
import kwu.esgproject.domain.Token;
import kwu.esgproject.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final JwtTokenProvider jwtTokenProvider;

    private final RedisService redisService;
    @Transactional
    public void login(Token tokenDto){
        RefreshToken refreshToken = RefreshToken.builder().keyEmail(tokenDto.getKey()).refreshToken(tokenDto.getRefreshToken()).build();

        String values = redisService.getValues("refreshToken:" + refreshToken.getKeyEmail());
        if(!values.isEmpty()){
            log.info("기존의 존재하는 refresh 토큰 삭제");
            redisService.deleteValues("refreshToken:" + refreshToken.getKeyEmail());
        }

        redisService.setValues("refreshToken:" + refreshToken.getKeyEmail(), refreshToken.getRefreshToken());
    }

    public String getRefreshToken(String keyEmail){
        // RedisTemplate을 사용하여 Redis에서 RefreshToken 가져오기
        return redisService.getValues("refreshToken:" + keyEmail);
    }

    public Map<String, String> validateRefreshToken(String keyEmail){

        String refreshToken = getRefreshToken(keyEmail);
        String createdAccessToken = jwtTokenProvider.validateRefreshToken(refreshToken);

        return createRefreshJson(createdAccessToken);
    }

    public Map<String, String> createRefreshJson(String createdAccessToken){
        Map<String, String> map = new HashMap<>();
        if(createdAccessToken == null){
            map.put("errorType", "Forbidden");
            map.put("status", "402");
            map.put("message", "Refresh 토큰이 만료되었습니다. 로그인이 필요합니다.");

            return map;
        }

        //기존에 존재하는 accessToken 제거
        map.put("status", "200");
        map.put("message", "Refresh 토큰을 통한 Access Token 생성이 완료되었습니다.");
        map.put("accessToken", createdAccessToken);

        return map;
    }
}
