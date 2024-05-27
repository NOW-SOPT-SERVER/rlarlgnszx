package server.sopt.week2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import server.sopt.week2.auth.redis.service.RefreshTokenService;
import server.sopt.week2.common.jwt.dto.CreateTokenByRefreshTokenResponse;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {

    private final RefreshTokenService refreshTokenService;
    @PostMapping("/refreshs")
    public ResponseEntity<CreateTokenByRefreshTokenResponse> reissueToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return refreshTokenService.refreshToken(request, response);
    }

}
