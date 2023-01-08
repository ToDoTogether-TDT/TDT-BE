package TDT.backend.repository.notice;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Notice;
import TDT.backend.entity.NoticeCategory;

import java.util.List;

public interface CustomNoticeRepository {

    List<NoticeResponseDto> findAllNoticeByMember(Member member);


    List<NoticeResponseDto> findAllByMemberIdAndStudyIdAndNoticeCategory(Long memberId, Long studyId, NoticeCategory noticeCategory);
}
