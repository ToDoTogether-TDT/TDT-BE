package TDT.backend.repository.comment;

import TDT.backend.dto.comment.CommentRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static TDT.backend.entity.QComment.*;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public List<CommentRes> findCommentsByPostId(Long postId) {

        return queryFactory
                .select(Projections.fields(CommentRes.class,
                        comment.member.nickname.as("writer"), comment.content, comment.createdAt))
                .from(comment)
                .where(comment.post.id.eq(postId))
                .fetch();
    }
}
