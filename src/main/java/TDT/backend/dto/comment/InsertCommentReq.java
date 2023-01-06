package TDT.backend.dto.comment;

import TDT.backend.entity.Comment;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.entity.Team;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertCommentReq {

    private String content;


    public Comment toEntity(Post post, Member member) {
        return Comment.builder()
                .post(post)
                .member(member)
                .content(this.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Comment toEntity(Team team, Member member) {
        return new Comment(team, member, this.content, LocalDateTime.now());
    }
}
