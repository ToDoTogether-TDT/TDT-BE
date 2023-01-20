package TDT.backend.dto.schedule;

import TDT.backend.entity.Schedule;
import TDT.backend.entity.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleAddReqDto {

    private LocalDateTime date;
    private List<ScheduleTitleDto> schedules;

    public Schedule toEntity(Team team, ScheduleTitleDto scheduleTitleDto) {
        return Schedule.builder()
                .team(team)
                .title(scheduleTitleDto.getTitle())
                .endAt(this.getDate())
                .build();
    }

    @Getter
    public static class ScheduleTitleDto {
        private String title;
    }

}
