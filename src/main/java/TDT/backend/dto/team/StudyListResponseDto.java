package TDT.backend.dto.team;

import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class StudyListResponseDto {

    private String writer;
    private String title;
    private Category category;
    private String introduction;
    private List<MemberDto> memberDto = new ArrayList<>();

    @QueryProjection
    public StudyListResponseDto(Team team, Member member) {
        this.writer = team.getName();
        this.title = team.getTitle();
        this.category = team.getCategory();
        this.introduction = team.getIntroduction();
        this.memberDto = (List<MemberDto>) new MemberDto(member.getNickname(), member.getPicture(), member.getIntroduction());
    }

    @Getter
    @AllArgsConstructor
    static class MemberDto{
        private String nickname;
        private String image;
        private String introduction;
    }
}
