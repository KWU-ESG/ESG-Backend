package kwu.esgproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    private Long refreshTokenId;
    private String refreshToken;
    private String keyEmail;
}
