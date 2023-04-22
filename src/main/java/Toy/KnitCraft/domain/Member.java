package Toy.KnitCraft.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String password;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @Builder
    public Member(String email, String username,String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
