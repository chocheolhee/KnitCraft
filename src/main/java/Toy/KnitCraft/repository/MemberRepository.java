package Toy.KnitCraft.repository;

import Toy.KnitCraft.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
