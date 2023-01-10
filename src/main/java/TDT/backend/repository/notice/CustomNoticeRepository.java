package TDT.backend.repository.notice;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.dto.notice.StudyNoticeResponseDto;
import TDT.backend.entity.Member;

import java.util.List;

public interface CustomNoticeRepository {

    List<NoticeResponseDto> findAllNoticeByMember(Member member);


    List<StudyNoticeResponseDto> findStudyJoinNoticeByMemberIdAndStudyId(Long memberId, Long studyId);
}
