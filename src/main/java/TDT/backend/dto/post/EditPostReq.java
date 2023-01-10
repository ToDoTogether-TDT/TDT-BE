package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EditPostReq {
    private String title;
    private String content;
    private Category category;
}
