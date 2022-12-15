package TDT.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Member member;
    private String content;
    private LocalDateTime createdAt;
}
