package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertPostReq {
    private String title;
    private String content;
    private String writer;
    private Category category;

    // 테스트용
    @Builder
    public InsertPostReq(String title, String content, String writer, Category category) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.category = category;
    }
}
