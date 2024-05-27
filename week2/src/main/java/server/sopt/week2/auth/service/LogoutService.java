package server.sopt.week2.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import server.sopt.week2.auth.UserAuthentication;
import server.sopt.week2.auth.redis.domain.Token;
import server.sopt.week2.auth.repository.RedisTokenRepository;
import server.sopt.week2.common.jwt.JwtTokenProvider;
import server.sopt.week2.common.jwt.JwtValidationType;

import static server.sopt.week2.common.jwt.JwtValidationType.EXPIRED_JWT_TOKEN;
import static server.sopt.week2.common.jwt.JwtValidationType.VALID_JWT;

@RequiredArgsConstructor
@Service
public class LogoutService implements LogoutHandler {
    private final RedisTokenRepository redisTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return;
            }
            jwt = authHeader.substring(7);
            Long memberId = jwtTokenProvider.getUserFromJwt(jwt);
            redisTokenRepository.findByRefreshToken(jwt).ifPresent(redisTokenRepository::delete);
    }
}

