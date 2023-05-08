package Toy.KnitCraft.domain;

import Toy.KnitCraft.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String username;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private final List<Session> sessions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Comment> comments = new ArrayList<>();

    @Builder
    public Member(String email, String username, String password, Address address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public void addSession() {
        sessions.add(Session.builder()
                .member(this)
                .build()
        );
    }
}
