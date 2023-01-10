package TDT.backend.repository.notice;


import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.dto.notice.QNoticeResponseDto;
import TDT.backend.dto.notice.QStudyNoticeResponseDto;
import TDT.backend.dto.notice.StudyNoticeResponseDto;
import TDT.backend.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomNoticeRepositoryImpl implements CustomNoticeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NoticeResponseDto> findAllNoticeByMember(Member member) {

        QNotice notice = QNotice.notice;
        return jpaQueryFactory.select(new QNoticeResponseDto(notice))
                .from(notice)
                .where(notice.receiver.id.eq(member.getId()))
                .fetch();
    }


    @Override
    public List<StudyNoticeResponseDto> findStudyJoinNoticeByMemberIdAndStudyId(Long memberId, Long studyId) {
        QTeamMember teamMember = QTeamMember.teamMember;
        QNotice notice = QNotice.notice;
//        List<NoticeResponseDto> fetch =
        return jpaQueryFactory.select(new QStudyNoticeResponseDto(teamMember, notice))
                .from(notice)
                .join(teamMember)
                .on(teamMember.team.id.eq(studyId).and(teamMember.status.eq(MemberStatus.guest)))
                .where(notice.receiver.id.eq(memberId).and(notice.noticeCategory.eq(NoticeCategory.studyJoin)))
                .fetch();

//                .and(notice.noticeCategory.eq(NoticeCategory.valueOf(noticeCategory)))
//                .from(teamMember,)
//                .join(teamMember.member, notice.receiver).fetchJoin()
//                .where(teamMember.team.id.eq(studyId).and(teamMember.status.eq(MemberStatus.guest))
//                        .and(notice.noticeCategory.eq(NoticeCategory.valueOf(noticeCategory)))).fetch();
//        return fetch;
    }
}
