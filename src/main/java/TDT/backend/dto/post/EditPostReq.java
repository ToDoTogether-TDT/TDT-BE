package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import lombok.Builder;
import lombok.Data;

@Data
public class EditPostReq {
    private String nickname;
    private String title;
    private String content;
    private Category category;

    // 테스트용
    @Builder
    public EditPostReq(String nickname, String title, String content, Category category) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
