package TDT.backend.repository.message;

import TDT.backend.dto.message.MessageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepositoryCustom {

    Page<MessageResponseDto> findAllByIdAndPageable(Long id, Pageable pageable);

}
