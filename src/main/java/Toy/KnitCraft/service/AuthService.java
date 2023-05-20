package Toy.KnitCraft.service;

import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.exception.AlreadyExistsEmailException;
import Toy.KnitCraft.repository.MemberRepository;
import Toy.KnitCraft.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(Signup signup) {
        Optional<Member> memberOptional = memberRepository.findByEmail(signup.getEmail());
        if (memberOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        String encryptedPassword = passwordEncoder.encode(signup.getPassword());

        Member member = Member.builder()
                .email(signup.getEmail())
                .password(encryptedPassword)
                .username(signup.getUsername())
                .build();
        memberRepository.save(member);

    }
}
