package server.sopt.week2.controller.auth.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    @Value("${kakao.client_id}")
    private String clientId;
    private String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    private final KakaoAuthApi kakaoAuthApi;
    private final KakaoApiClient kakaoapi;

    public KaKaoDto.KakaoUserInfoResponse login(
            final String authorizationCode
    ){
        KakaoTokenResponse tokenResponse = getAccessTokenFromKakao(authorizationCode);
        return getUserInfo(tokenResponse.getAccessToken());
    }
    public KakaoTokenResponse getAccessTokenFromKakao(String code) {
        return kakaoAuthApi.getOAuth2AccessToken("authorization_code",clientId,"http://localhost:8080/api/v1/auth/login/kakao",code);
    }
    public KaKaoDto.KakaoUserInfoResponse getUserInfo(
            final String accessToken
    ){
        return kakaoapi.getUserInformation("Bearer " + accessToken);
    }
}
