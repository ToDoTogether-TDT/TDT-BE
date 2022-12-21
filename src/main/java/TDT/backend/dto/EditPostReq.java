package TDT.backend.dto;

import TDT.backend.entity.Category;
import lombok.Data;

@Data
public class EditPostReq {
    private String title;
    private String content;
    private Category category;
}
