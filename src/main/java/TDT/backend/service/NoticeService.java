package TDT.backend.service;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.entity.Member;
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

    public void getStudyNotice(Long studyId, String noticeCategory, Member member) {

        if (teamMemberRepository.isLeader(member.getId(), studyId)) {
            noticeRepository.findAllByMemberIdAndNoticeCategory(member.getId(), noticeCategory);
        }
    }
}
