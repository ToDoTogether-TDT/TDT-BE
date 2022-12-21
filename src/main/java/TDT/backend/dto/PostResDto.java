package TDT.backend.dto;

import TDT.backend.entity.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private Category category;
    private Integer commentsLength;

}
