package TDT.backend.dto.post;

import TDT.backend.dto.comment.CommentRes;
import TDT.backend.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PostDetailResDto {
    private Long postId;
    private String writer;
    private String title;
    private String content;
    private Category category;
    private List<CommentRes> comments;

    @Builder
    public PostDetailResDto(Long postId, String writer, String title, String content, Category category, List<CommentRes> comments) {
        this.postId = postId;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.category = category;
        this.comments = comments;
    }
}
