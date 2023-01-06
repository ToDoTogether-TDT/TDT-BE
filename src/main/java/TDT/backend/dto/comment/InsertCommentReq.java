package TDT.backend.dto.comment;

import TDT.backend.entity.Comment;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertCommentReq {
    private String writer;
    private String content;


    public Comment toEntity(Post post, Member member) {
        return Comment.builder()
                .post(post)
                .member(member)
                .content(this.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
