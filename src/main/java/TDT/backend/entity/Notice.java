package TDT.backend.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private NoticeCategory noticeCategory;

    private String contents;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member receiver;


}
