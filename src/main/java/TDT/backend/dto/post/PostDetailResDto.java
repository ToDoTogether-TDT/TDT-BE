package TDT.backend.dto.post;

import TDT.backend.dto.comment.CommentRes;
import TDT.backend.entity.Category;
import TDT.backend.entity.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDetailResDto {
    private Long postId;
    private String writer;
    private String picture;
    private String title;
    private String content;
    private Category category;
    private List<CommentRes> comments;
    private LocalDateTime createdAt;
    private int view;

    @Builder
    public PostDetailResDto(Long postId, String writer, String picture, String title, String content, Category category, List<CommentRes> comments, LocalDateTime createdAt, int view) {
        this.postId = postId;
        this.writer = writer;
        this.picture = picture;
        this.title = title;
        this.content = content;
        this.category = category;
        this.comments = comments;
        this.createdAt = createdAt;
        this.view = view;
    }

    public static PostDetailResDto of(Post post, List<CommentRes> comments) {

        return PostDetailResDto.builder()
                .postId(post.getId())
                .writer(post.getMember().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .comments(comments)
                .createdAt(post.getCreatedAt())
                .view(post.getView())
                .build();
    }
}
