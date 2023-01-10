package TDT.backend.dto.team;

import TDT.backend.entity.Category;
import TDT.backend.entity.MemberStatus;
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
    private List<MemberDto> memberDto;

    @QueryProjection
    public StudyListResponseDto(Team team) {
        List<MemberDto> memberDto = new ArrayList<>();
        team.getTeamMembers().forEach(teamMember -> {
            if (!teamMember.getStatus().equals(MemberStatus.guest)) {
                memberDto.add(new MemberDto(teamMember.getMember().getNickname(), teamMember.getMember().getPicture(), teamMember.getMember().getIntroduction()));
            }
            this.writer = team.getName();
            this.title = team.getTitle();
            this.category = team.getCategory();
            this.introduction = team.getIntroduction();
            this.memberDto = memberDto;
        });
    }

    @Getter
    @AllArgsConstructor
    static class MemberDto {
        private String nickname;
        private String image;
        private String introduction;
    }
}
