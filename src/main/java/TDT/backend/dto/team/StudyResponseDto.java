package TDT.backend.dto.team;

import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Schedule;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StudyResponseDto {

    private String writer;
    private String title;
    private Category category;
    private String introduction;
    private List<ScheduleDto> scheduleDto;


    @QueryProjection
    public StudyResponseDto(Team team, Member member) {
        this.writer = team.getName();
        this.title = team.getTitle();
        this.category = team.getCategory();
        this.introduction = team.getIntroduction();
    }


    @Builder
    static class ScheduleDto {
        private Long scheduleId;
        private String title;
        private List<MemberDto> memberDto;
    }

    @Builder
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
