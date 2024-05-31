package server.sopt.week2.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.sopt.week2.common.jwt.JwtTokenProvider;
import server.sopt.week2.controller.auth.kakao.KakaoAuthService;
import server.sopt.week2.controller.auth.kakao.KakaoTokenResponse;
import server.sopt.week2.dto.UserJoinResponse;
import server.sopt.week2.error.ErrorMessage;
import server.sopt.week2.exception.BusinessException;
import server.sopt.week2.service.MemberService;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KakaoAuthService kakaoAuthService;

    @GetMapping("/login/kakao")
    public ResponseEntity<KakaoTokenResponse> kakaoLogin(
            @RequestParam(value = "code") String code
    ) {
        KakaoTokenResponse res = kakaoAuthService.getAccessTokenFromKakao(code);
        return ResponseEntity.ok().body(res);
    }

}
