package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String title;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime createdAt;

    @Builder
    public Post(Member member, String title, String content, Category category, LocalDateTime createdAt) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }
}
