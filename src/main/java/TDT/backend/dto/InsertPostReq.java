package TDT.backend.dto;

import TDT.backend.entity.Category;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertPostReq {
    private String title;
    private String content;
    private String writer;
    private Category category;
    private LocalDateTime createAt;
}
