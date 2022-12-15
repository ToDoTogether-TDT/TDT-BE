package TDT.backend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
    @ManyToOne
    private Member sender;
    @ManyToOne
    private Member receiver;
    private String content;
    private LocalDateTime sendAt;

}
