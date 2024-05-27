package server.sopt.week2.common.jwt.dto;

public record CreateTokenByRefreshTokenResponse(
        String accessToken
) {

    public static CreateTokenByRefreshTokenResponse of(String s) {
        return new CreateTokenByRefreshTokenResponse(s);
    }
}
