package TDT.backend.dto.team;

import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Schedule;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyResponseDto {

    private String writer;
    private String title;
    private Category category;
    private String introduction;
    private MemberDto memberDto;


    @QueryProjection
    public StudyResponseDto(Team team, Member member) {
        this.writer = team.getName();
        this.title = team.getTitle();
        this.category = team.getCategory();
        this.introduction = team.getIntroduction();
        this.memberDto = new MemberDto(member.getNickname(), member.getPicture(), member.getIntroduction());
    }

    @Getter
    @AllArgsConstructor
    static class MemberDto{
        private String nickname;
        private String image;
        private String introduction;
        /**
         * 스케줄 추가해야함
         * 댓글 추가
         */
    }

}
