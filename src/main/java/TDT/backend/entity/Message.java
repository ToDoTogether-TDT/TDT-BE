package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member sender;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiver;
    private String content;
    private LocalDateTime sendAt;

}
