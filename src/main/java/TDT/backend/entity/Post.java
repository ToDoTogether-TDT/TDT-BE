package TDT.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne
    private Member member;
    private String title;
    private String content;
    private Tag tag;
    private LocalDateTime createdAt;
}
