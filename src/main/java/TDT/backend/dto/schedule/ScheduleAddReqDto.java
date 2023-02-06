package TDT.backend.dto.schedule;

import TDT.backend.entity.Schedule;
import TDT.backend.entity.Team;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleAddReqDto {

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date date;
    private List<ScheduleTitleDto> schedules;

    public Schedule toEntity(Team team, ScheduleTitleDto scheduleTitleDto) {
        return Schedule.builder()
                .team(team)
                .title(scheduleTitleDto.getTitle())
                .endAt(date)
                .build();
    }

    @Getter
    public static class ScheduleTitleDto {
        private String id;
        private String title;
    }

}
