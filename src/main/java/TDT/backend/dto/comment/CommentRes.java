package TDT.backend.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRes {
    private String writer;
    private String content;
    private LocalDateTime createdAt;
}
