package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @Column(nullable = false)
    private Integer view = 0;

    @Builder
    public Post(Member member, String title, String content, Category category, LocalDateTime createdAt) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }

    public void edit(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void addView() {
        this.view += 1;
    }
}
