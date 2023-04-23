package Toy.KnitCraft.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String username;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Comment> comments = new ArrayList<>();

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @Builder
    public Member(String email, String username, String password, Address address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
