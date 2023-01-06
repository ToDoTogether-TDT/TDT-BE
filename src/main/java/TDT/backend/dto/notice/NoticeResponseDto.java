package TDT.backend.dto.notice;

import TDT.backend.entity.Member;
import TDT.backend.entity.NoticeCategory;
import TDT.backend.entity.TeamMember;
import TDT.backend.service.NoticeService;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NoticeResponseDto {

    private NoticeCategory noticeCategory;
    private LocalDateTime localDateTime;
    private Member member;


    @QueryProjection
    public NoticeResponseDto(TeamMember teamMember, NoticeCategory noticeCategory) {
        this.member = teamMember.getMember();
        this.noticeCategory = noticeCategory;
    }
}
