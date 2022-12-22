package TDT.backend.repository.post;

import TDT.backend.dto.post.PostPageResDto;
import TDT.backend.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostPageResDto> getList(Pageable pageable, Category category);
}
