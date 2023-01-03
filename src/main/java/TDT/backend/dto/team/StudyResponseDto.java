package TDT.backend.dto.team;

import TDT.backend.dto.schedule.ScheduleDto;
import TDT.backend.entity.Category;
import TDT.backend.entity.Member;
import TDT.backend.entity.Team;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class StudyResponseDto {

    private String writer;
    private String title;
    private Category category;
    private String introduction;
    private List<ScheduleDto> todos = new ArrayList<>();


    @QueryProjection
    public StudyResponseDto(Team team, Member member) {
        this.writer = team.getName();
        this.title = team.getTitle();
        this.category = team.getCategory();
        this.introduction = team.getIntroduction();
    }

    @Builder
    public StudyResponseDto(String writer, String title, Category category, String introduction, List<ScheduleDto> scheduleDto) {
        this.writer = writer;
        this.title = title;
        this.category = category;
        this.introduction = introduction;
        this.todos = scheduleDto;
    }

    /**
     * 스케줄 추가해야함
     * 댓글 추가
     */
}
