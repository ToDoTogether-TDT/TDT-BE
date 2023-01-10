package TDT.backend.dto.notice;

import TDT.backend.entity.Notice;
import TDT.backend.entity.NoticeCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponseDto {
    private Long noticeId;
    private NoticeCategory noticeCategory;
    private LocalDateTime dateTime;
    private String contents;

    @QueryProjection
    public NoticeResponseDto(Notice notice) {
        this.noticeId = notice.getId();
        this.noticeCategory = notice.getNoticeCategory();
        this.dateTime = notice.getDateTime();
        this.contents = notice.getContents();
    }
}
