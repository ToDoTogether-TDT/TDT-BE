package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import lombok.Data;

@Data
public class EditPostReq {
    private String nickname;
    private String title;
    private String content;
    private Category category;
}
