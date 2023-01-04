package TDT.backend.repository.post;

import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.dto.post.QPostPageResDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Page<PostPageResDto> getList(Pageable pageable, String category) {
//
//        BooleanBuilder builder = new BooleanBuilder();
//        if(category != null) {
//            builder.and(post.category.eq(category));
//        }


        List<PostPageResDto> content = queryFactory
                .select(Projections.fields(PostPageResDto.class,
                        post.id, post.title, post.content, post.member.nickname, post.createdAt, post.category,
                        post.comments.size().as("commentsLength"), post.view))
                .from(post)
                .where(post.category.eq(Category.valueOf(category)))
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(post.count())
                .from(post)
                .where(post.category.eq(Category.valueOf(category)));

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Override
    public void deleteByMemberId(Long memberId) {

        queryFactory.delete(post).where(post.member.id.eq(memberId)).execute();
    }

    @Override
    public Page<PostPageResDto> findAllByPageable(Pageable pageable) {
        JPAQuery<PostPageResDto> q = queryFactory.select(new QPostPageResDto(post, post.member)).from(post)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(q.fetch(), pageable, q.fetchCount());
    }
}
