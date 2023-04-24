package Toy.KnitCraft.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private final List<Comment> comments = new ArrayList<>();

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * 연관관계 메서드
     */
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }
}
