package TDT.backend.repository.team;
import TDT.backend.dto.team.QStudyResponseDto;
import TDT.backend.dto.team.QStudyListResponseDto;
import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.dto.team.StudyResponseDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.QTeamMember;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static TDT.backend.entity.QTeam.team;

@RequiredArgsConstructor
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    private final JPAQueryFactory jpaQueryFactory;



    @Override
    public Page<StudyListResponseDto> findAllByCategoryAndPageable(String category, Pageable pageable) {
        QTeamMember teamMember = QTeamMember.teamMember;
        JPAQuery<StudyListResponseDto> query = jpaQueryFactory.select(new QStudyListResponseDto(team, teamMember.member))
                .from(team)
                .join(team.teamMembers, teamMember).fetchJoin()
                .where(team.category.eq(Category.valueOf(category)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(),pageable,query.fetchCount());
    }

    @Override
    public StudyResponseDto findByIdAndCategory(Integer studyId, String category) {
        QTeamMember teamMember = QTeamMember.teamMember;
        StudyResponseDto dto = jpaQueryFactory.select(new QStudyResponseDto(team, teamMember.member))
                .from(team)
                .join(team.teamMembers, teamMember).fetchJoin()
                .where(team.id.eq(Long.valueOf(studyId))).fetchOne();

        return dto;
    }

}
