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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public SessionResponse login(@RequestBody Login login) {
        String accessToken = authService.signIn(login);
        return new SessionResponse(accessToken);
    }
}
