package TDT.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    public static Notice of(Member member, NoticeCategory noticeCategory) {
        return Notice.builder().receiver(member).dateTime(LocalDateTime.now())
                .contents("스터디 가입요청").noticeCategory(noticeCategory).build();

    }

    public static Notice ofComment(Member member, NoticeCategory noticeCategory, String contents) {
        return Notice.builder().receiver(member).dateTime(LocalDateTime.now())
                .contents(contents).noticeCategory(noticeCategory).build();

    }
}
