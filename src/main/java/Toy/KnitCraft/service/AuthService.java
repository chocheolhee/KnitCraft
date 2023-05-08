package Toy.KnitCraft.service;

import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.exception.InvalidSigninInformation;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.request.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signIn(Login login) {
        Member member = memberRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        member.addSession();
    }
}
