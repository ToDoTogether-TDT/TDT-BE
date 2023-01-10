package TDT.backend.repository.post;

import TDT.backend.dto.post.PostPageResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostPageResDto> getList(Pageable pageable, String category);

    void deleteByMemberId(Long memberId);

    Page<PostPageResDto> findAllByPageable(Pageable pageable);
}
