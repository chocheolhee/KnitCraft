package Toy.KnitCraft.controller;

import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.exception.InvalidRequest;
import Toy.KnitCraft.exception.InvalidSigninInformation;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;

    @PostMapping("/auth/login")
    public Member login(@RequestBody Login login) {
        log.info(">>>login={}", login);
        Member member = memberRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);

        return member;
    }
}
