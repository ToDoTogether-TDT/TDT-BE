package TDT.backend.dto.notice;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.TeamMember;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class NoticeResponseDto {

    private String noticeCategory;
    private LocalDateTime localDateTime;
    private Member member;

    @QueryProjection
    public NoticeResponseDto(TeamMember teamMember) {
        this.member = teamMember.getMember();
    }

//    @QueryProjection
//    public NoticeResponseDto(MemberDto memberDto) {
//        this.member = memberDto.getMember();
//    }


}
