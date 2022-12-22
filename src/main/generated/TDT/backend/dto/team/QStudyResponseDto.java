package TDT.backend.dto.team;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * TDT.backend.dto.team.QStudyResponseDto is a Querydsl Projection type for StudyResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStudyResponseDto extends ConstructorExpression<StudyResponseDto> {

    private static final long serialVersionUID = -939057485L;

    public QStudyResponseDto(com.querydsl.core.types.Expression<? extends TDT.backend.entity.Team> team, com.querydsl.core.types.Expression<? extends TDT.backend.entity.Member> member) {
        super(StudyResponseDto.class, new Class<?>[]{TDT.backend.entity.Team.class, TDT.backend.entity.Member.class}, team, member);
    }

}

