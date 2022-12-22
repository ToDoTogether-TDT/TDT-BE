package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostPageResDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private Category category;
    private Integer commentsLength;

}
