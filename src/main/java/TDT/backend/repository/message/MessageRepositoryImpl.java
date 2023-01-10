package TDT.backend.repository.message;

import TDT.backend.dto.message.MessageResponseDto;
import TDT.backend.dto.message.QMessageResponseDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static TDT.backend.entity.QMessage.message;

@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MessageResponseDto> findAllByIdAndPageable(Long id, Pageable pageable) {
        JPAQuery<MessageResponseDto> query = jpaQueryFactory.select(new QMessageResponseDto(message, message.sender))
                .from(message)
                .where(message.receiver.id.eq(id))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}
