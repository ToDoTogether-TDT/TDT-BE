package TDT.backend.repository.notice;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Notice;

import java.util.List;

public interface CustomNoticeRepository {

    List<NoticeResponseDto> findAllNoticeByMember(Member member);

    List<NoticeResponseDto> findAllByMemberIdAndNoticeCategory(Long memberId, String noticeCategory);
}
