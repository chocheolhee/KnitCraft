package Toy.KnitCraft.controller;

import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.exception.InvalidSigninInformation;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.request.Login;
import Toy.KnitCraft.response.SessionResponse;
import Toy.KnitCraft.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        String accessToken = authService.signIn(login);
        return new SessionResponse(accessToken);
    }
}
