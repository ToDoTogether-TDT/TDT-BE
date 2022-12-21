package TDT.backend.repository.post;

import TDT.backend.dto.PostResDto;
import TDT.backend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostResDto> getList(Pageable pageable, Category category);
}
