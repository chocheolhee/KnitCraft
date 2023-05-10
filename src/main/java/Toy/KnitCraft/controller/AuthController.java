package Toy.KnitCraft.controller;

import Toy.KnitCraft.config.data.UserSession;
import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.exception.InvalidSigninInformation;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.request.Login;
import Toy.KnitCraft.response.SessionResponse;
import Toy.KnitCraft.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/foo")
    public Long foo(UserSession userSession) {
        log.info(">>>{}",userSession.id);
        return userSession.id;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody Login login) {
        String accessToken = authService.signIn(login);
        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost") // todo 서버 환경에 따른 분리 필요
                .path("/")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
