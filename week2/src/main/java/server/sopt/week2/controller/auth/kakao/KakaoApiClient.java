package server.sopt.week2.controller.auth.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {
    @GetMapping("/v2/user/me")
    KaKaoDto.KakaoUserInfoResponse getUserInformation(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

}
