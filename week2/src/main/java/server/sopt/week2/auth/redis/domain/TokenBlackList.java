package server.sopt.week2.auth.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "BlackListToken")
@AllArgsConstructor
@Getter
@Builder
public class TokenBlackList {
    @Indexed
    @Id
    private Long id;

    @Indexed
    private String accessToken;

    public TokenBlackList of(Long id, String refreshToken) {
        return TokenBlackList.builder()
                .id(id)
                .accessToken(refreshToken)
                .build();
    }
}
