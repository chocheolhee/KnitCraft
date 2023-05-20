package Toy.KnitCraft.service;

import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.exception.AlreadyExistsEmailException;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public void signup(Signup signup) {
        Optional<Member> memberOptional = memberRepository.findByEmail(signup.getEmail());
        if (memberOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        Member member = Member.builder()
                .email(signup.getEmail())
                .password(signup.getPassword())
                .username(signup.getUsername())
                .build();
        memberRepository.save(member);

    }
}
