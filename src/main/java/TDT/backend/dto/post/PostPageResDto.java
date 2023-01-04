package TDT.backend.dto.post;

import TDT.backend.dto.team.StudyListResponseDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostPageResDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private Category category;
    private Integer commentsLength;
    private Integer view;

    @QueryProjection
    public PostPageResDto(Post post, Member member) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.nickname = member.getNickname();
        this.createdAt = post.getCreatedAt();
        this.category = post.getCategory();
        this.view = post.getView();
    }

}
