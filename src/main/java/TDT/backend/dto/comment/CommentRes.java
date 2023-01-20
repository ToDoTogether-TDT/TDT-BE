package TDT.backend.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentRes {
    private Long commentId;
    private String writer;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public CommentRes(String writer, String content, LocalDateTime createdAt) {
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
    }
}
