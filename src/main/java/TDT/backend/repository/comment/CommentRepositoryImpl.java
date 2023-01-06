package TDT.backend.repository.comment;

import TDT.backend.dto.comment.CommentRes;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static TDT.backend.entity.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<CommentRes> findCommentsByPostIdOrStudyId(Long postId, Long studyId) {

        BooleanBuilder builder = new BooleanBuilder();
        if(postId != null) builder.and(comment.post.id.eq(postId));
        else builder.and(comment.team.id.eq(studyId));

        return queryFactory
                .select(Projections.fields(CommentRes.class,
                        comment.member.nickname.as("writer"), comment.content, comment.createdAt))
                .from(comment)
                .where(builder)
                .fetch();
    }
}
