package Toy.KnitCraft.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    @ManyToOne
    private Member member;

    @Builder
    public Session(Member member) {
        this.accessToken = randomUUID().toString();
        this.member = member;
    }
}
