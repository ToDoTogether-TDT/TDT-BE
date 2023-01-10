package TDT.backend.service;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.dto.notice.StudyNoticeResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.Notice;
import TDT.backend.entity.NoticeCategory;
import TDT.backend.entity.TeamMember;
import TDT.backend.exception.BusinessException;
import TDT.backend.exception.ExceptionCode;
import TDT.backend.repository.member.MemberRepository;
import TDT.backend.repository.notice.NoticeRepository;
import TDT.backend.repository.teamMember.TeamMemberRepository;
import TDT.backend.service.member.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final TeamMemberRepository teamMemberRepository;
    private final NoticeRepository noticeRepository;


    public List<NoticeResponseDto> getNotice(MemberDetails member) {

        return noticeRepository.findAllNoticeByMember(member.getMember());


    }

    public List<StudyNoticeResponseDto> getStudyNotice(Long studyId, Member member) {

        if (teamMemberRepository.isLeader(member.getId(), studyId)) {
            List<StudyNoticeResponseDto> dto = noticeRepository.findStudyJoinNoticeByMemberIdAndStudyId(member.getId(), studyId);
//            dto.stream().forEach(dtos -> dtos.updateToStudy(noticeCategory));

            return dto;
        } else {
            return null;
        }

    }

    public void deleteNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.NOTICE_NOT_EXISTS));

        noticeRepository.delete(notice);
    }

    /**
     * Todo PostNotice
     * @return
     */
    public List<StudyNoticeResponseDto> getPostNotice() {
        return null;
    }
}
