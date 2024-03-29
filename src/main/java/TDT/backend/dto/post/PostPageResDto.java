package TDT.backend.dto.post;

import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Post;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostPageResDto {

    private Long id;
    private String title;
    private String content;
    private String nickname;
    private String image;
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
        this.image = member.getPicture();
        this.createdAt = post.getCreatedAt();
        this.category = post.getCategory();
        this.commentsLength = post.getComments().size();
        this.view = post.getView();
    }

}
