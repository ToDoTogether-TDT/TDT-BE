package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class InsertPostReq {
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private Category category;

    private LocalDateTime createdAt;

    // 테스트용
    @Builder
    public InsertPostReq(String title, String content, Category category, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Post toEntity(Member member) {
        return Post.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .member(member)
                .category(this.getCategory())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
