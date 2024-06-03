package server.sopt.week2.auth.redis.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.sopt.week2.auth.UserAuthentication;
import server.sopt.week2.auth.redis.domain.Token;
import server.sopt.week2.auth.repository.RedisTokenRepository;
import server.sopt.week2.common.jwt.JwtTokenProvider;
import server.sopt.week2.common.jwt.JwtValidationType;
import server.sopt.week2.common.jwt.dto.CreateTokenByRefreshTokenResponse;

import static server.sopt.week2.common.jwt.JwtValidationType.EXPIRED_JWT_TOKEN;
import static server.sopt.week2.common.jwt.JwtValidationType.VALID_JWT;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTokenRepository redisTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public void save(
            final Long memberId,
            final String refreshToken
    ) {
        redisTokenRepository.save(
                Token.of(memberId, refreshToken)
        );
    }

    public Long findIdByRefreshToken(
            final String refreshToken
    ) {
        return redisTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("NO TOKEN")).getId();
    }

    @Transactional
    public void deleteRefreshToken(
            final Long memberId
    ) {
        Token token = redisTokenRepository.findById(memberId)
                .orElseThrow(
                        () -> new RuntimeException("token없음")
                );

        redisTokenRepository.delete(token);
    }


    public ResponseEntity<CreateTokenByRefreshTokenResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        token = authHeader.substring(7);
        JwtValidationType jwtValidationType = jwtTokenProvider.validateToken(token);

        if (jwtValidationType == VALID_JWT) {
            Long memberId = jwtTokenProvider.getUserFromJwt(token);
            Token refreshToken = redisTokenRepository.findById(memberId).orElseThrow(
                    () -> new RuntimeException("refresh token 만료 혹은 없음")
            );
            UserAuthentication authentication = UserAuthentication.createUserAuthentication(memberId);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Location", memberId.toString())
                    .body(CreateTokenByRefreshTokenResponse.of(jwtTokenProvider.issueAccessToken(authentication)));
        }
        throw new RuntimeException("error~!");
    }
}
