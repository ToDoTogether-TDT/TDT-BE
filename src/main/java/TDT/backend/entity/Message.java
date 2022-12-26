package TDT.backend.entity;

import TDT.backend.dto.message.MessageRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public static Message of(MessageRequestDto dto, Member sender,Member receiver) {
        return Message.builder()
                .content(dto.getContents())
                .sender(sender)
                .receiver(receiver).build();
    }

}

