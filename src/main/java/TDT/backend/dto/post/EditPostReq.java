package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditPostReq {

    private String nickname;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
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
