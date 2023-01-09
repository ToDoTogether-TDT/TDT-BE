package TDT.backend.dto.notice;

import TDT.backend.dto.member.MemberDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Notice;
import TDT.backend.entity.NoticeCategory;
import TDT.backend.entity.TeamMember;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class NoticeResponseDto {

    private NoticeCategory noticeCategory;
    private LocalDateTime localDateTime;
    private Member member;
    /**
     * Todo member -> NoticeMemberResponseDto 객체로 변경하기
     */
    private NoticeMemberResponseDto noticeMember;

    @QueryProjection
    public NoticeResponseDto(TeamMember teamMember, Notice notice) {

        this.noticeMember = NoticeMemberResponseDto.builder().member(TeamMember.toMember(teamMember)).build();
        this.member = TeamMember.toMember(teamMember);
        this.localDateTime = notice.getDateTime();
        this.noticeCategory = notice.getNoticeCategory();
    }

//    @QueryProjection
//    public NoticeResponseDto(MemberDto memberDto) {
//        this.member = memberDto.getMember();
//    }


    public void updateToStudy(NoticeCategory noticeCategory) {
        this.noticeCategory = noticeCategory;
    }

}
