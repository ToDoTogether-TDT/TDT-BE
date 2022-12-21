package TDT.backend.repository.team;

import TDT.backend.dto.team.QStudyListRes;
import TDT.backend.dto.team.StudyListRes;
import TDT.backend.entity.Category;
import TDT.backend.entity.QTeamMember;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static TDT.backend.entity.QMember.member;
import static TDT.backend.entity.QTeam.team;

@RequiredArgsConstructor
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    /**
     *
     private String writer;
     private String title;
     private String introduction;
     private Member member;
     */
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StudyListRes> findAllByCategoryAndPageable(String category, Pageable pageable) {
        QTeamMember teamMember = QTeamMember.teamMember;
        JPAQuery<StudyListRes> query = queryFactory.select(new QStudyListRes(team, teamMember.member))
                .from(team)
                .join(team.teamMembers, teamMember).fetchJoin()
                .where(team.category.eq(Category.valueOf(category)))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(),pageable,query.fetchCount());
    }

}
