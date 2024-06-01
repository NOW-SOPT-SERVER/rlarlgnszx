package server.sopt.week2.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import server.sopt.week2.auth.UserAuthentication;
import server.sopt.week2.repo.MemberRepository;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final MemberRepository memberRepository;
//    private final MemberRepository memberRepository;
    //    private static final String USER_ID = "userId";
    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;
    //    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 1L;
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 24 * 1000L * 14;

//    User 개인 고유번호
    private static final String USER_ID = "userId";

    @Value("${jwt.secret}")
    private final String JWT_SCRET = "nowsoptnowsoptnownownononwonwownwownownwowno";


    public String issueRefreshToken(final Authentication authentication) {

        return generateToken(authentication, REFRESH_TOKEN_EXPIRATION_TIME, false);
    }

    public String issueRefreshToken(final String socialId) {
        Long memberId = memberRepository.findBySocialId(socialId).orElseThrow().getId();
        UserAuthentication authentication = UserAuthentication.createUserAuthentication(memberId);
        return generateToken(authentication, REFRESH_TOKEN_EXPIRATION_TIME, false);
    }

    public String issueAccessToken(final Authentication authentication) {
        return generateToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME, true);
    }


    public String generateToken(Authentication authentication, Long tokenExpirationTime, boolean isAccessToken) {

        final Date now = new Date();
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
//                .setSubject(isAccessToken ? "access" : "refresh")
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));      // 만료 시간
        claims.put(USER_ID, authentication.getPrincipal());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header
                .setClaims(claims) // Claim
                .signWith(getSigningKey()) // Signature
                .compact();
    }

    private SecretKey getSigningKey() {
        String encodedKey = Base64.getEncoder().encodeToString(JWT_SCRET.getBytes()); //SecretKey 통해 서명 생성
        return Keys.hmacShaKeyFor(encodedKey.getBytes());   //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용
    }

    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            return JwtValidationType.VALID_JWT;
        } catch (MalformedJwtException ex) {
            return JwtValidationType.INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException ex) {
            return JwtValidationType.EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException ex) {
            return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException ex) {
            return JwtValidationType.EMPTY_JWT;
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserPk(String token) {
        return getBody(token).getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }

    public boolean isAccessToken(String token) {
        return Objects.equals(getBody(token).getSubject(), "access");
    }
}