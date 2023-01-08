package TDT.backend.repository.notice;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.dto.notice.QNoticeResponseDto;
import TDT.backend.entity.Member;
import TDT.backend.entity.NoticeCategory;
import TDT.backend.entity.QTeamMember;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomNoticeRepositoryImpl implements CustomNoticeRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<NoticeResponseDto> findAllNoticeByMember(Member member) {
        return null;
    }


    @Override
    public List<NoticeResponseDto> findAllByMemberIdAndStudyIdAndNoticeCategory(Long memberId, Long studyId, String noticeCategory) {
        QTeamMember teamMember = QTeamMember.teamMember;


        List<NoticeResponseDto> fetch = jpaQueryFactory.select(new QNoticeResponseDto(teamMember)
                )
                .from(teamMember)
                .where(teamMember.team.id.eq(studyId)).fetch();

        return fetch;
    }
}
