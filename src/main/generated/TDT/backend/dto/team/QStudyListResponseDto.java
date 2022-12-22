package TDT.backend.dto.team;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * TDT.backend.dto.team.QStudyListResponseDto is a Querydsl Projection type for StudyListResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStudyListResponseDto extends ConstructorExpression<StudyListResponseDto> {

    private static final long serialVersionUID = -2092845899L;

    public QStudyListResponseDto(com.querydsl.core.types.Expression<? extends TDT.backend.entity.Team> team, com.querydsl.core.types.Expression<? extends TDT.backend.entity.Member> member) {
        super(StudyListResponseDto.class, new Class<?>[]{TDT.backend.entity.Team.class, TDT.backend.entity.Member.class}, team, member);
    }

}

