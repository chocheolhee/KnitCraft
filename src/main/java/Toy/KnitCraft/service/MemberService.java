package Toy.KnitCraft.service;

import Toy.KnitCraft.domain.Member;
import Toy.KnitCraft.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long register(Member member) {
        memberRepository.save(member);
        return member.getId();
    }
}
