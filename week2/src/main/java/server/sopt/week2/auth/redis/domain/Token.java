package server.sopt.week2.auth.redis.domain;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken",timeToLive = 60 * 60 * 24 * 1000L * 14)
@AllArgsConstructor
@Getter
@Builder
public class Token {
//    @Indexed
    @Id
    private Long id;

    @Indexed
    private String refreshToken;

    public static Token of(Long id, String refreshToken) {
        return Token.builder()
                .id(id)
                .refreshToken(refreshToken)
                .build();
    }
}
