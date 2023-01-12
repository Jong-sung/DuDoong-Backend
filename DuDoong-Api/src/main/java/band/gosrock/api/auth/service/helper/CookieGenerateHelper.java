package band.gosrock.api.auth.service.helper;


import band.gosrock.api.auth.model.dto.response.TokenAndUserResponse;
import band.gosrock.common.annotation.Helper;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

@Helper
@RequiredArgsConstructor
public class CookieGenerateHelper {
    private final Environment env;

    public HttpHeaders getTokenCookies(TokenAndUserResponse tokenAndUserResponse) {
        String[] activeProfiles = env.getActiveProfiles();
        String sameSite = "None";

        if (Arrays.stream(activeProfiles).toList().contains("prod")) {
            sameSite = "Strict";
        }
        ResponseCookie accessToken =
                ResponseCookie.from("accessToken", tokenAndUserResponse.getAccessToken())
                        .path("/")
                        .maxAge(tokenAndUserResponse.getAccessTokenAge())
                        .sameSite(sameSite)
                        .httpOnly(true)
                        .secure(true)
                        .build();
        ResponseCookie refreshToken =
                ResponseCookie.from("refreshToken", tokenAndUserResponse.getRefreshToken())
                        .path("/")
                        .maxAge(tokenAndUserResponse.getRefreshTokenAge())
                        .sameSite(sameSite)
                        .httpOnly(true)
                        .secure(true)
                        .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, accessToken.toString());
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshToken.toString());
        return httpHeaders;
    }
}