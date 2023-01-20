package TDT.backend.repository.team;

import TDT.backend.dto.team.*;
import TDT.backend.entity.Category;
import TDT.backend.entity.MemberStatus;
import TDT.backend.entity.QTeamMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static TDT.backend.entity.QTeam.team;
import static TDT.backend.entity.QTeamMember.*;

@RequiredArgsConstructor
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<StudyListResponseDto> findAllByCategoryAndPageable(String category, Pageable pageable) {
        JPAQuery<StudyListResponseDto> query = jpaQueryFactory.select(new QStudyListResponseDto(team))
                .from(team)
                .where(team.category.eq(Category.valueOf(category)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    @Override
    public Page<StudyListResponseDto> findAllByPageable(Pageable pageable) {
        JPAQuery<StudyListResponseDto> query = jpaQueryFactory.select(new QStudyListResponseDto(team))
                .from(team)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    @Override
    public List<StudyJoinReqMemberDto> findStudyJoinReqMembers(Long studyId) {
        return jpaQueryFactory
                .select(Projections.fields(StudyJoinReqMemberDto.class,
                        teamMember.member.id.as("memberId"), teamMember.member.nickname, teamMember.member.picture.as("image")))
                .from(teamMember)
                .where(teamMember.team.id.eq(studyId).and(teamMember.status.eq(MemberStatus.guest)))
                .fetch();
    }

    @Override
    public StudyResponseDto findByIdAndCategory(Long studyId, String category) {
        QTeamMember teamMember = QTeamMember.teamMember;
        StudyResponseDto dto = jpaQueryFactory.select(new QStudyResponseDto(team, teamMember.member))
                .from(team)
                .join(team.teamMembers, teamMember).fetchJoin()
                .where(team.id.eq(studyId)).fetchOne();

        return dto;
    }

}
