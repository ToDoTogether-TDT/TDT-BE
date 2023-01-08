package TDT.backend.service;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.NoticeCategory;
import TDT.backend.entity.TeamMember;
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

    public List<NoticeResponseDto> getStudyNotice(Long studyId, NoticeCategory noticeCategory, Member member) {

        if (teamMemberRepository.isLeader(member.getId(), studyId)) {
            List<NoticeResponseDto> dto = noticeRepository.findAllByMemberIdAndStudyIdAndNoticeCategory(member.getId(), studyId, noticeCategory);
//            dto.stream().forEach(dtos -> dtos.updateToStudy(noticeCategory));

            return dto;
        } else {
            return null;
        }

    }

    /**
     * Todo PostNotice
     * @return
     */
    public List<NoticeResponseDto> getPostNotice() {
        return null;
    }
}
