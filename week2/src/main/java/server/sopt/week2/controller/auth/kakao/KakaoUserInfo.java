package server.sopt.week2.controller.auth.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KakaoUserInfo { // 카카오 API를 이용해 토큰을 전송하여 유저 정보를 요청

    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    //TODO 후에 API 구현
//    public KaKaoDto.KakaoUserInfoResponse getUserInfo(String token) {
////        Flux<KaKaoDto.KakaoUserInfoResponse> response = webClient.get()
////                .uri(USER_INFO_URI)
////                .header("Authorization", "Bearer " + token)
////                .retrieve()
////                .bodyToFlux(KaKaoDto.KakaoUserInfoResponse.class);
//        return response.blockFirst();
}
