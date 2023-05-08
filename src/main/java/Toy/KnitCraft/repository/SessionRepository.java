package Toy.KnitCraft.repository;

import Toy.KnitCraft.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
