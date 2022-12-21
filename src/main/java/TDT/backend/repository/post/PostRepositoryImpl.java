package TDT.backend.repository.post;

import TDT.backend.dto.PostResDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static TDT.backend.entity.QPost.*;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<PostResDto> getList(Pageable pageable, Category category) {

        BooleanBuilder builder = new BooleanBuilder();
        if(category != null) {
            builder.and(post.category.eq(category));
        }


        List<PostResDto> content = queryFactory
                .select(Projections.fields(PostResDto.class,
                        post.id, post.title, post.content, post.member.nickname, post.createdAt, post.category, post.comments.size().as("commentsLength")))
                .from(post)
                .where(builder)
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(post.count())
                .from(post)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }
}
