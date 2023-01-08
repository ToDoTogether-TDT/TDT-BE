package TDT.backend.entity;

import TDT.backend.dto.message.MessageRequestDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private NoticeCategory noticeCategory;

    private String contents;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiver;



    public static Notice of(Member member,NoticeCategory noticeCategory) {
        return Notice.builder().receiver(member).dateTime(LocalDateTime.now())
                .contents("가입인사").noticeCategory(noticeCategory).build();

    }
}
