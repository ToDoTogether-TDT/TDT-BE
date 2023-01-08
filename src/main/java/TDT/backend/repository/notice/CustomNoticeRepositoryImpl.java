package TDT.backend.repository.notice;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.dto.notice.QNoticeResponseDto;
import TDT.backend.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static TDT.backend.entity.QNotice.notice;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.list;

@RequiredArgsConstructor
public class CustomNoticeRepositoryImpl implements CustomNoticeRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<NoticeResponseDto> findAllNoticeByMember(Member member) {
        return null;
    }


    @Override
    public List<NoticeResponseDto> findAllByMemberIdAndStudyIdAndNoticeCategory(Long memberId, Long studyId, NoticeCategory noticeCategory) {
        QTeamMember teamMember = QTeamMember.teamMember;
        QNotice notice = QNotice.notice;
//        List<NoticeResponseDto> fetch =
        return jpaQueryFactory.select(new QNoticeResponseDto(teamMember, notice))
                .from(notice, teamMember)
                .where(teamMember.team.id.eq(studyId), teamMember.status.eq(MemberStatus.guest))
                .fetch();

//                .and(notice.noticeCategory.eq(NoticeCategory.valueOf(noticeCategory)))
//                .from(teamMember,)
//                .join(teamMember.member, notice.receiver).fetchJoin()
//                .where(teamMember.team.id.eq(studyId).and(teamMember.status.eq(MemberStatus.guest))
//                        .and(notice.noticeCategory.eq(NoticeCategory.valueOf(noticeCategory)))).fetch();
//        return fetch;
    }
}
